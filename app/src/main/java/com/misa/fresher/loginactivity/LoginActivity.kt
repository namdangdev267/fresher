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

        binding.tvSignUp.setOnClickListener {
            binding.textInputLayoutConfirmPassword.visibility = View.VISIBLE
            binding.btnLogin.visibility = View.GONE
            binding.btnSignUp.visibility = View.VISIBLE
            binding.tvSignUp.visibility = View.GONE
        }

        binding.btnSignUp.setOnClickListener {
            binding.textInputLayoutConfirmPassword.visibility = View.GONE
            binding.btnLogin.visibility = View.VISIBLE
            binding.btnSignUp.visibility = View.GONE
            binding.tvSignUp.visibility = View.VISIBLE
        }

        binding.btnLogin.setOnClickListener {
            logIn(intentLogin)
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

    private fun signUp(intent: Intent) {
        val userName = binding.tvUsername.text.toString()
        val passWord = binding.tvPassword.text.toString()
        val user = User(userName, passWord)
        val signUpAPI = APIClient.newRetrofitInstance().create(UserInterfaceService::class.java)

        CoroutineScope(IO).launch {
            try {
                val respone = signUpAPI.userLogin(user)

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
}