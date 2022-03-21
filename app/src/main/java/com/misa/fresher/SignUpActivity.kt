package com.misa.fresher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.misa.fresher.databinding.ActivityLoginBinding
import com.misa.fresher.databinding.ActivitySignUpBinding
import com.misa.fresher.model.User
import com.misa.fresher.retrofit.ApiClient
import com.misa.fresher.retrofit.api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    private val bindding: ActivitySignUpBinding by lazy {
        getInflater(layoutInflater)
    }
    val getInflater: (LayoutInflater) -> ActivitySignUpBinding
        get() = ActivitySignUpBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindding.root)
        val intent = Intent(this, LoginActivity::class.java)
        bindding.btnSignIn.setOnClickListener {
            registerUser(intent)
        }
    }

    private fun registerUser(intent: Intent) {
        val userName = bindding.tvUsername.text.toString()
        val password = bindding.tvPassword.text.toString()
        val rtPassword = bindding.tvPasswordReType.text.toString()
        if (userName == "") {
            applicationContext.showToast("Tên đăng nhập không được để trống")
        } else if (password == "") {
            applicationContext.showToast("Mật khẩu không được để trống")
        } else if (password!=rtPassword){
            applicationContext.showToast("Mật khẩu không khớp")
        } else{
            val user = User(userName, password)
            val signInApi = api.getInstance().create(ApiClient::class.java)
            bindding.pbSignIn.isVisible = true
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = signInApi.signUp(user)
                    if (response.isSuccessful && response.body() != null) {
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    } else {
                        withContext(Dispatchers.Main) {
                            bindding.pbSignIn.isVisible = false
                            applicationContext.showToast("Error Occurred : ${response.errorBody()}")
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        bindding.pbSignIn.isVisible = false
                        applicationContext.showToast("Error Occurred : ${e.printStackTrace()}")
                    }
                }
            }
        }
    }
}