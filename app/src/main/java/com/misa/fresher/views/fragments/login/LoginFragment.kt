package com.misa.fresher.views.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.misa.fresher.R
import com.misa.fresher.data.database.AppDatabase
import com.misa.fresher.databinding.FragmentLoginBinding
import com.misa.fresher.models.Country
import com.misa.fresher.models.User
import com.misa.fresher.models.enums.LoginMode
import com.misa.fresher.services.ServiceBuilder
import com.misa.fresher.services.UserService
import com.misa.fresher.utils.SimpleImageArrayAdapter
import com.misa.fresher.views.activities.MainActivity
import com.misa.fresher.views.customViews.CustomToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
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
        configSpinner()
        transitionFragemnt(view)
        configOtherView()
    }

    private fun configOtherView() {
        loginViewModel.loginMode.observe(viewLifecycleOwner, Observer {
            Log.e("thiss", loginViewModel.loginMode.value.toString())
            if (loginViewModel.loginMode.value == LoginMode.SIGNUP) {
                binding.tvLoginMode.text = "Log in"
                binding.tvLogin.text = "Sign up"
//                binding.ilConfirmPassword.visibility = View.VISIBLE
//                binding.etConfirmPassword.visibility = View.VISIBLE
            } else {
                binding.tvLoginMode.text = "Sign up"
                binding.tvLogin.text = "Log in"
                binding.etLoginPassword.setText("")
//                binding.etConfirmPassword.visibility = View.GONE
//                binding.ilConfirmPassword.visibility = View.GONE

            }


        })
    }

    private fun transitionFragemnt(view: View) {
        binding.tvLogin.setOnClickListener {
            if (checkLogin()) {
                if (loginViewModel.loginMode.value == LoginMode.LOGIN) {
                    login(view)
                } else {
                    signUp(view)
                }
            }
        }

        binding.tvLoginMode.setOnClickListener {
            loginViewModel.changeLoginMode()
        }
    }

    private fun signUp(view: View) {
        val api = ServiceBuilder.buildService(UserService::class.java)
        binding.progress.visibility = View.VISIBLE
        CoroutineScope(IO).launch {
            try {
                val respond = api.signUp(
                    User(
                        binding.etLoginEmail.text.toString(),
                        binding.etLoginPassword.text.toString()
                    )
                )

                if (respond.isSuccessful && respond.body() != null) {
                    binding.progress.visibility = View.GONE
                    loginViewModel.changeLoginMode()
                    Log.e(this.javaClass.simpleName, respond.message())
                } else {
                    withContext(Main) {
                        binding.progress.visibility = View.GONE
                        CustomToast.makeText(
                            view.context,
                            "Email exist. Please enter other email.",
                            )
                    }
                }
            } catch (e: Exception) {
                withContext(Main)
                {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(
                        view.context,
                        "Something went wrong. Please try other account.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun login(view: View) {
        val api = ServiceBuilder.buildService(UserService::class.java)
        CoroutineScope(IO).launch {
            try {
                val respond = api.signIn(
                    User(
                        binding.etLoginEmail.text.toString(),
                        binding.etLoginPassword.text.toString()
                    )
                )

                if (respond.isSuccessful && respond.body() != null) {
                    loginSuccess()
                } else {
                    withContext(Main) {
                        CustomToast.makeText(
                            view.context,
                            "Username or password is incorrect. Please check again."
                        )
                    }
                }
            } catch (e: Exception) {
                withContext(Main) {
                    Toast.makeText(
                        view.context,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun loginSuccess() {
        val intent = Intent(this.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun configSpinner() {
        this.context?.let {
            val spinAdapter = SimpleImageArrayAdapter(
                it,
                R.layout.item_country,
                mutableListOf(
                    Country(R.drawable.ic_uk_flag, "English"),
                    Country(R.drawable.ic_vi_flag, "Vietnam"),
                    Country(R.drawable.ic_my_flag, "Myanma")
                )
            )
            binding.spinLoginFlag.adapter = spinAdapter
        }
    }

    fun checkLogin(): Boolean {
        if (binding.etLoginEmail.text.toString().isEmpty()) {
            CustomToast.makeText(
                requireContext(),
                "Username must not be empty"
            )
            return false
        } else if (binding.etLoginPassword.text.toString().isEmpty()) {
            CustomToast.makeText(
                requireContext(),
                "Password must not be empty"
            )
            return false
        } else if (!binding.etLoginEmail.text.toString().contains("@gmail.com")) {
            CustomToast.makeText(
                requireContext(), "Bad email address.Please check again."
            )
            return false
        } else if (binding.etLoginPassword.text.toString().length < 6) {
            CustomToast.makeText(
                requireContext(),
                "Password should be at least 6 characters"
            )
            return false
        }
//        else if (loginViewModel.loginMode.value == LoginMode.SIGNUP && binding.etConfirmPassword.text.toString() != binding.etLoginPassword.text.toString()) {
//            CustomToast.makeText(
//                requireContext(),
//                "Your confirm password is incorrect. Please check again."
//            )
//            return false
//        }
        else {
            return true
        }

    }

}