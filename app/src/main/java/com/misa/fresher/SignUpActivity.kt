package com.misa.fresher

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.misa.fresher.databinding.ActivitySignUpBinding
import com.misa.fresher.model.User
import com.misa.fresher.retrofit.ApiHelper
import com.misa.fresher.retrofit.ApiInterface
import kotlinx.coroutines.*
import java.lang.Exception

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy { getInflater(layoutInflater) }
    val getInflater: (LayoutInflater) -> ActivitySignUpBinding
        get() = ActivitySignUpBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        binding.btnSignUp.setOnClickListener {
            signUpAccount(intent)
        }
        binding.tvLogin.setOnClickListener {
            onBackPressed()
        }
    }

    /**
     *Đăng ký tài khoản mới
     *@author:NCPhuc
     *@date:3/22/2022
     **/
    private fun signUpAccount(intent: Intent) {
        val user = binding.tietSignUpUserName.text.toString()
        val pass = binding.tietSignUpPassword.text.toString()
        val rePass = binding.tietReEnterPassword.text.toString()
        if (user.isEmpty()) {
            application.showToast("Tài khoản không được để trống!")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            application.showToast("Tài khoản không đúng định dạng email!")
        } else if (pass.isEmpty()) {
            application.showToast("Mật khẩu không được bỏ trống!")
        } else if (rePass.isEmpty()) {
            application.showToast("Mật khẩu nhập lại không được bỏ trống!")
        } else if (pass != rePass) {
            application.showToast("Mật khẩu không khớp nhau!")
        } else if (!checkForInternet(this)) {
            application.showToast("Không có kết nối mạng")
        } else {
            val userInfor = User(user, pass)
            val resIn = ApiHelper.getInstance().create(ApiInterface::class.java)
            binding.flProgressBar.isVisible=true
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val signUp = resIn.signUp(userInfor)
                    if (signUp.isSuccessful && signUp.body() != null) {
                        withContext(Dispatchers.Main)
                        {
                            application.showToast("Đăng ký tài khoản thành công")
                        }
                        startActivity(intent)
                    } else {
                        withContext(Dispatchers.Main) {
                            application.showToast(signUp.message())
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        application.showToast(e.message.toString())
                    }
                }
            }
        }
    }

    /**
     *Kiểm tra kết nối Internet
     *@author:NCPhuc
     *@date:3/22/2022
     **/
    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION") return networkInfo.isConnected
        }

    }
}