package com.misa.fresher

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.misa.fresher.databinding.ActivityLoginBinding
import com.misa.fresher.model.User
import com.misa.fresher.retrofit.ApiHelper
import com.misa.fresher.retrofit.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy { getInflater(layoutInflater) }
    val getInflater: (LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            signIn(intent)
        }
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    /**
     *Đăng nhập vào ứng dụng
     *@author:NCPhuc
     *@date:3/22/2022
     **/
    private fun signIn(intent: Intent) {
        val user = User(binding.tietUsername.text.toString(), binding.tietPassword.text.toString())
        val resIn = ApiHelper.getInstance().create(ApiInterface::class.java)
        if (binding.tietUsername.text.toString().isEmpty() || binding.tietPassword.text.toString()
                .isEmpty()
        ) {
            application.showToast("Tài khoản/mật khẩu không được để trống!")
        } else if (!checkForInternet(this)) {
            application.showToast("Không có kết nối mạng!")
        } else {
            binding.flProgessBarSignIn.isVisible=true
            CoroutineScope(IO).launch {
                try {
                    val signIn = resIn.signIn(user)
                    if (signIn.isSuccessful && signIn.body() != null) {
                        startActivity(intent)
                    } else {
                        withContext(Dispatchers.Main) {
                            application.showToast(signIn.errorBody().toString())
                            binding.flProgessBarSignIn.isVisible=false
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
     *Kiểm tra kết nối mạng
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