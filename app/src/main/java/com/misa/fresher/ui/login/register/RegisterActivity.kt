package com.misa.fresher.ui.login.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.misa.fresher.data.model.User
import com.misa.fresher.databinding.ActivitySignUpBinding
import com.misa.fresher.showToast
import com.misa.fresher.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    private val binding: ActivitySignUpBinding by lazy {
        getInflater(layoutInflater)
    }
    val getInflater: (LayoutInflater) -> ActivitySignUpBinding
        get() = ActivitySignUpBinding::inflate
    private var mPresenter: RegisterPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initPresenter()
        binding.btnSignIn.setOnClickListener { view ->
            checkValid()
        }
    }

    private fun checkValid() {
        val userName = binding.tvUsername.text.toString()
        val password = binding.tvPassword.text.toString()
        val retypePassword = binding.tvPasswordReType.text.toString()

        val user = User(userName, password)
        if (userName.isEmpty()) {
            applicationContext.showToast("Tên đăng nhập không được để trống")
        } else if (password.isEmpty()) {
            applicationContext.showToast("Mật khẩu không được để trống")
        } else if (retypePassword != password) {
            applicationContext.showToast("Mật khẩu không khớp")
        } else {
            mPresenter?.register(user)
        }
    }

    override fun registerSuccess() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun initPresenter() {
        if(mPresenter == null){
            mPresenter = RegisterPresenter().also {
                it.attach(this)
            }
        }
    }

    override fun showErrorMessage(msg: String) {
        applicationContext.showToast(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter = null
    }
}