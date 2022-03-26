package com.misa.fresher.loginactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.misa.fresher.MainActivity
import com.misa.fresher.databinding.FragmentLoginBinding
import com.misa.fresher.showToast
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

        transitionFragemnt()
        configureOtherView()
    }

    @SuppressLint("SetTextI18n")
    private fun configureOtherView() {
        loginViewModel.login.observe(viewLifecycleOwner, Observer {
            if (loginViewModel.login.value == LoginMode.SIGNUP) {
                binding.tvSignUp.text = "Log in"
                binding.btnLogIn.text = "Sign up"
                binding.textInputLayoutConfirmPassword.visibility = View.VISIBLE
            } else {
                binding.tvSignUp.text = "Sign up"
                binding.btnLogIn.text = "Log in"
                binding.textInputLayoutConfirmPassword.visibility = View.GONE
            }
        })
    }

    private fun transitionFragemnt() {
        binding.btnLogIn.setOnClickListener {
            if (checkLogin()) {
                if (loginViewModel.login.value == LoginMode.LOGIN) {
                    login()
                } else {
                    signUp()
                }
            }
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

    private fun loginSuccess() {
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