package kma.longhoang.beta.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import kma.longhoang.beta.R
import kma.longhoang.beta.model.UserModel
import kma.longhoang.beta.retrofit.APIService
import kma.longhoang.beta.showNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUp(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    private fun signUp(view: View) {
        val btnSignUp = view.findViewById<Button>(R.id.button_sign_up)
        val tvSignIn = view.findViewById<TextView>(R.id.text_sign_in)
        btnSignUp.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.edt_signup_email).text.toString()
            val pass = view.findViewById<EditText>(R.id.edt_signup_password).text.toString()
            val confirmPass = view.findViewById<EditText>(R.id.edt_confirm_password).text.toString()
            if (email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                showNote(requireContext(), "Bạn phải nhập đầy đủ thông tin!")
            } else if (pass != confirmPass) {
                showNote(requireContext(), "Mật khẩu không trùng nhau!")
            } else if (pass.length < 6 || confirmPass.length < 6) {
                showNote(requireContext(), "Mật khẩu phải có tối thiểu 6 ký tự!")
            } else {
                val user = UserModel(email, pass)
                registerAccount(user)
            }
        }
        tvSignIn.setOnClickListener {
            view.findViewById<LinearLayout>(R.id.view_signup).isVisible = false
            activity?.onBackPressed()
        }
    }

    private fun registerAccount(user: UserModel) {
        val apiService = Retrofit.Builder()
            .baseUrl("https://identitytoolkit.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val postData = apiService.postToSignUp(user)
            CoroutineScope(Dispatchers.Main).launch {
                if (postData.body() != null && postData.body()?.registered == false) {
                    showNote(requireContext(), "Đăng ký thành công!")
                    activity?.onBackPressed()
                }
                if (postData.body() == null) {
                    showNote(requireContext(), "Tài khoản đã tồn tại!")
                }
            }
        }
    }
}