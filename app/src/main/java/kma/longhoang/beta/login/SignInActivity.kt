package kma.longhoang.beta.login

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import kma.longhoang.beta.R
import kma.longhoang.beta.model.UserModel
import kma.longhoang.beta.retrofit.APIService
import kma.longhoang.beta.showNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        signIn()
    }

    private fun signIn() {
        val btnSignIn = findViewById<Button>(R.id.button_sign_in)
        btnSignIn.setOnClickListener {
            val email = findViewById<EditText>(R.id.edt_email).text
            val pass = findViewById<EditText>(R.id.edt_password).text
            if (email.isEmpty() || pass.isEmpty()) {
                showNote(this, "Bạn phải nhập đầy đủ thông tin!")
            } else if (pass.length < 6) {
                showNote(this, "Mật khẩu phải có tối thiểu 6 ký tự!")
            } else {
                val user = UserModel(email.toString(), pass.toString())
                checkAccount(user)
            }
        }
        findViewById<TextView>(R.id.text_sign_up).setOnClickListener {
            val fragmentManager = supportFragmentManager
            findViewById<LinearLayout>(R.id.view_login).isVisible = false
            fragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.exit_to_left,
                    R.anim.slide_in_left,
                    R.anim.exit_to_right
                )
                .addToBackStack(null)
                .replace(R.id.fragment_container_view, SignUpFragment()).commit()
        }
    }

    private fun checkAccount(user: UserModel) {
        val apiService = Retrofit.Builder()
            .baseUrl("https://identitytoolkit.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)
        val dialog = AlertDialog.Builder(this@SignInActivity).create()
        dialog.setMessage("loading...")
        dialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            val postData = apiService.postToSignIn(user)
            CoroutineScope(Dispatchers.Main).launch {
                if (postData.body() != null && postData.body()?.registered == true) {
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    finish()
                    dialog.dismiss()
                }
                if (postData.body() == null) {
                    showNote(applicationContext, "Email hoặc mật khẩu không đúng!")
                    dialog.dismiss()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!findViewById<LinearLayout>(R.id.view_login).isVisible) {
            findViewById<LinearLayout>(R.id.view_login).isVisible = true
        }
        super.onBackPressed()
    }
}