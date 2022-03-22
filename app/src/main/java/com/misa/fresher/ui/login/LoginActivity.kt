package com.misa.fresher.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.misa.fresher.MainActivity
import com.misa.fresher.ui.login.register.RegisterActivity
import com.misa.fresher.databinding.ActivityLoginBinding
import com.misa.fresher.model.User
import com.misa.fresher.showToast

/**
* Khởi tạo class
* @Auther : NTBao
* @date : 3/21/2022
**/
class LoginActivity : AppCompatActivity(), LoginContract.View {
    private var mPresenter : LoginPresenter? =null
    private val bindding: ActivityLoginBinding by lazy {
        getInflater(layoutInflater)
    }
    val getInflater: (LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    override fun loginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    override fun initPresenter() {
        mPresenter = LoginPresenter().also { it.attach(this) }
    }

    override fun showErrorMessage(msg: String) {
        application.showToast("Error :"+msg)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindding.root)
        initPresenter()
        bindding.btnSignIn.setOnClickListener {
            view -> checkValid()
        }
        bindding.tvSignUp.setOnClickListener {
            view -> showRegisterActivity()
        }
    }
    private fun showRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    private fun checkValid() {
        val userName = bindding.tvUsername.text.toString()
        val password = bindding.tvPassword.text.toString()
        val processBar = bindding.pbSignIn
        val user = User(userName,password)
        if (userName.isEmpty()) {
            applicationContext.showToast("Tên đăng nhập không được để trống")
        } else if (password.isEmpty()) {
            applicationContext.showToast("Mật khẩu không được để trống")
        } else {
            processBar.isVisible = true
            mPresenter?.login(user)
        }
    }
    companion object{
       const val REGISTERTAG = "registerFragment"
    }

}

