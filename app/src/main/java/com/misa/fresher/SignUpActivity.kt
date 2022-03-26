package com.misa.fresher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.misa.fresher.api.Api
import com.misa.fresher.api.ApiClient
import com.misa.fresher.models.User
import kotlinx.coroutines.*
import java.lang.Exception

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val intent = Intent(this,LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        val btnDangKi = findViewById<Button>(R.id.btn_dangki)
        btnDangKi.setOnClickListener(){
            signUp(intent)
        }
    }

    private fun signUp(intent: Intent) {
        val etTaiKhoan = findViewById<EditText>(R.id.et_taikhoan)
        val etPassWord = findViewById<EditText>(R.id.et_password)
        val etConfirmPassWord = findViewById<EditText>(R.id.et_confirmpassword)

        val taiKhoan = etTaiKhoan.text.toString()
        val passWord = etPassWord.text.toString()
        val confirmPassWord = etConfirmPassWord.text.toString()
        val user = User(taiKhoan,passWord)
        val api = Api.getInstance().create(ApiClient::class.java)

        if(taiKhoan.isEmpty() || passWord.isEmpty() || confirmPassWord.isEmpty())
            Toast.makeText(this,"Thông tin không được để trống",Toast.LENGTH_SHORT).show()
        if(passWord != confirmPassWord)
            Toast.makeText(this,"Mật khẩu không khớp",Toast.LENGTH_SHORT).show()

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val signUp = api.signUp(user)
                if(signUp.isSuccessful && signUp.body() != null){
                    withContext(Dispatchers.Main){
                        Toast.makeText(applicationContext,"Đăng kí thành công",Toast.LENGTH_SHORT).show()
                    }
                    startActivity(intent)
                }
            }catch (e : Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}