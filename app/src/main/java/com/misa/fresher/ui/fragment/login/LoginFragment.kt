package com.misa.fresher.ui.fragment.login

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.misa.fresher.R
import com.misa.fresher.data.models.User
import com.misa.fresher.databinding.DialogSelectWorkingShopBinding
import com.misa.fresher.databinding.FragmentLoginBinding
import com.misa.fresher.loginactivity.APIClient
import com.misa.fresher.loginactivity.LoginMode
import com.misa.fresher.loginactivity.LoginViewModel
import com.misa.fresher.loginactivity.UserInterfaceService
import com.misa.fresher.showToast
import com.misa.fresher.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    val binding: FragmentLoginBinding by lazy { getInflater(layoutInflater) }

    val getInflater: (LayoutInflater) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transitionFragment()
        configureOtherView()
    }

    @SuppressLint("SetTextI18n")
    private fun configureOtherView() {
        loginViewModel.login.observe(viewLifecycleOwner) {
            if (loginViewModel.login.value == LoginMode.SIGNUP) {
                binding.tvSignUp.text = "Log in"
                binding.btnLogIn.text = "Sign up"
                binding.textInputLayoutConfirmPassword.visibility = View.VISIBLE
            } else {
                binding.tvSignUp.text = "Sign up"
                binding.btnLogIn.text = "Log in"
                binding.textInputLayoutConfirmPassword.visibility = View.GONE
            }
        }
    }

    private fun transitionFragment() {
        binding.btnLogIn.setOnClickListener {
//            if (checkLogin()) {
//                if (loginViewModel.login.value == LoginMode.LOGIN) {
//                    login()
            openDialogSelectWorkingShop()
//                } else {
//                    signUp()
//                }
//            }
        }

        binding.tvSignUp.setOnClickListener {
            loginViewModel.changeLoginMode()
        }
    }

    private fun signUp() {
        val api = APIClient.newRetrofitInstance().create(UserInterfaceService::class.java)
        binding.idLoadingPB.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respond = api.signUp(
                    User(
                        binding.tvUsername.text.toString(),
                        binding.tvPassword.text.toString()
                    )
                )

                if (respond.isSuccessful && respond.body() != null) {
                    binding.idLoadingPB.visibility = View.GONE
                    loginViewModel.changeLoginMode()
                    Log.e(this.javaClass.simpleName, respond.message())
                } else {
                    withContext(Dispatchers.Main) {
                        binding.idLoadingPB.visibility = View.GONE
                        requireContext().showToast("Email exist. Please enter other email.")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.idLoadingPB.visibility = View.GONE
                    requireContext().showToast("Error Occurred: ${e.message}")
                }
            }
        }
    }

    private fun login() {
        val api = APIClient.newRetrofitInstance().create(UserInterfaceService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respond = api.userLogin(
                    User(
                        binding.tvUsername.text.toString(),
                        binding.tvPassword.text.toString()
                    )
                )

                if (respond.isSuccessful && respond.body() != null) {
                    loginSuccess()
//                    openDialogSelectWorkingShop()
                } else {
                    withContext(Dispatchers.Main) {
                        requireContext().showToast("Username or password is incorrect. Please check again.")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    requireContext().showToast("Error Occurred: ${e.message}")
                }
            }
        }
    }

    private fun openDialogSelectWorkingShop() {
        val dialog = Dialog(requireContext())
        val mBinding = DialogSelectWorkingShopBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_select_working_shop)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowArtributes: WindowManager.LayoutParams = window.attributes
        windowArtributes.gravity = Gravity.CENTER
        window.attributes = windowArtributes
        dialog.setCancelable(true)
        dialog.show()
        mBinding.btnDone.setOnClickListener {
            requireContext().showToast("Checkkkkkkkkk DONE")
        }
    }

    private fun loginSuccess() {
//        val dialog = Dialog(requireContext())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.dialog_select_working_shop)
//        val window = dialog.window ?: return
//        window.setLayout(
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.WRAP_CONTENT
//        )
//        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        val windowArtributes: WindowManager.LayoutParams = window.attributes
//        windowArtributes.gravity = Gravity.CENTER
//        window.attributes = windowArtributes
//        dialog.setCancelable(true)
//        dialog.show()

        val intent = Intent(this.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun checkLogin(): Boolean {
        return if (binding.tvUsername.text.toString().isEmpty()) {
            requireContext().showToast("Username must not be empty")
            false
        } else if (binding.tvPassword.text.toString().isEmpty()) {
            requireContext().showToast("Password must not be empty")
            false
        } else if (binding.tvPassword.text.toString().length < 6) {
            requireContext().showToast("Password should be at least 6 characters")
            false
        } else if (loginViewModel.login.value == LoginMode.SIGNUP && binding.tvConfirmPassword.text.toString() != binding.tvPassword.text.toString()) {
            requireContext().showToast("Your confirm password is incorrect. Please check again.")
            false
        } else {
            true
        }

    }
}