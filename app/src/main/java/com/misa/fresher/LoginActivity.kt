package com.misa.fresher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.misa.fresher.api.Api
import com.misa.fresher.api.ApiClient
import com.misa.fresher.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener{
            val intent =Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            signIn(intent)
        }
        val tvSignIn = findViewById<TextView>(R.id.tv_signin)
        tvSignIn.setOnClickListener(){
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signIn(intent: Intent) {
        val etUserName = findViewById<EditText>(R.id.et_username)
        val etPassWord = findViewById<EditText>(R.id.et_password)
        val userName = etUserName.text.toString()
        val passWord = etPassWord.text.toString()
        val user = User(userName,passWord)
        val api = Api.getInstance().create(ApiClient::class.java)

        if (userName.isEmpty() ||passWord.isEmpty())
            Toast.makeText(this,"Thông tin không được để trống",Toast.LENGTH_SHORT).show()
        else {
            CoroutineScope(IO).launch {
                try{
                    val signIn = api.signIn(user)
                    if(signIn.isSuccessful && signIn.body() != null)
                        startActivity(intent)
                    else
                        withContext(Dispatchers.Main){
                            Toast.makeText(applicationContext,signIn.errorBody().toString(),Toast.LENGTH_SHORT).show()
                        }
            }catch (e : Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_SHORT).show()
                }
            }
            }
        }
    }
}