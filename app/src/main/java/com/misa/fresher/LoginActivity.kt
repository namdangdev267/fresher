package com.misa.fresher

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.misa.fresher.databinding.ActivityLoginBinding
import com.misa.fresher.model.User
import com.misa.fresher.retrofit.ApiClient
import com.misa.fresher.retrofit.api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
/**
* Khởi tạo class
* @Auther : NTBao
* @date : 3/21/2022
**/
class LoginActivity : AppCompatActivity() {
    private val bindding: ActivityLoginBinding by lazy {
        getInflater(layoutInflater)
    }
    val getInflater: (LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindding.root)
        val intent = Intent(this, MainActivity::class.java)
        val intentSignUp = Intent(this, SignUpActivity::class.java)
        bindding.btnSignIn.setOnClickListener {
            checkLogin(intent)
        }
        bindding.tvSignUp.setOnClickListener {
            startActivity(intentSignUp)
        }
    }
    private fun checkLogin(intent: Intent) {
        val userName = bindding.tvUsername.text.toString()
        val password = bindding.tvPassword.text.toString()
        if (userName == "") {
            applicationContext.showToast("Tên đăng nhập không được để trống")
        } else if (password == "") {
            applicationContext.showToast("Mật khẩu không được để trống")
        } else {
            val user = User(userName, password)
            val signInApi = api.getInstance().create(ApiClient::class.java)
            bindding.pbSignIn.isVisible = true
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = signInApi.signIn(user)
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

    companion object {
        const val USER = "user"
    }

}

