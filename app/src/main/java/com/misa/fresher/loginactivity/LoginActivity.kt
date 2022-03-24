package com.misa.fresher.loginactivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.misa.fresher.MainActivity
import com.misa.fresher.databinding.ActivityLoginBinding
import com.misa.fresher.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy { getInflater(layoutInflater) }

    val getInflater: (LayoutInflater) -> ActivityLoginBinding get() = ActivityLoginBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intentLogin = Intent(this, MainActivity::class.java)

        binding.run {
            tvSignUp.setOnClickListener {
                textInputLayoutConfirmPassword.visibility = View.VISIBLE
                btnLogIn.visibility = View.GONE
                btnSignUp.visibility = View.VISIBLE
                tvSignUp.visibility = View.GONE
            }

            btnSignUp.setOnClickListener {
                textInputLayoutConfirmPassword.visibility = View.GONE
                btnLogIn.visibility = View.VISIBLE
                btnSignUp.visibility = View.GONE
                tvSignUp.visibility = View.VISIBLE
//                checkValid()
            }

            btnLogIn.setOnClickListener {
                logIn(intentLogin)
            }
        }

    }

    private fun logIn(intent: Intent) {
        val userName = binding.tvUsername.text.toString()
        val passWord = binding.tvPassword.text.toString()
        val user = User(userName, passWord)

        val loginAPI = APIClient.newRetrofitInstance().create(UserInterfaceService::class.java)

        if (userName.isEmpty()) {
            applicationContext.showToast("Username must not be empty")
        } else if (passWord.isEmpty()) {
            applicationContext.showToast("Password must not be empty")
        }

        CoroutineScope(IO).launch {
            try {
                val respone = loginAPI.userLogin(user)

                if (respone.isSuccessful && respone.body() != null) {
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                } else {
                    withContext(Main) {
                        applicationContext.showToast("Error Occurred: ${respone.message()}")
                    }
                }
            } catch (e: Exception) {
                applicationContext.showToast("Error Occurred: ${e.message}")
            }
        }
    }

//    private fun checkValid() {
//        val userName = binding.tvUsername.text.toString()
//        val passWord = binding.tvPassword.text.toString()
//        val confirmPassword = binding.tvConfirmPassword.text.toString()
//        val user = User(userName, passWord)
//
//        if (userName.isBlank())
//            applicationContext.showToast("Username must not be empty")
//
//        else if (passWord.isBlank())
//            applicationContext.showToast("Password must not be empty")
//
//        else if (confirmPassword.isBlank())
//            applicationContext.showToast("Please confirm password")
//
//        else if (passWord.compareTo(confirmPassword) != 0)
//            applicationContext.showToast("Password do not matching")
//
//        else
//            signUp(user)
//    }
//
//    private fun signUp(user: User){
//        val signUpAPI = APIClient.newRetrofitInstance().create(UserInterfaceService::class.java)
//        CoroutineScope(IO).launch {
//            try {
//                val respone = signUpAPI.signUp(user)
//
//                if (respone.isSuccessful && respone.body() != null) {
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                } else {
//                    withContext(Main) {
//                        applicationContext.showToast("Error Occurred: ${respone.message()}")
//                    }
//                }
//            } catch (e: Exception) {
//                applicationContext.showToast("Error Occurred: ${e.message}")
//            }
//        }
//    }
}