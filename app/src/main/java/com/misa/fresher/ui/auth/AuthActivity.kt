package com.misa.fresher.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.misa.fresher.R
import com.misa.fresher.base.BaseActivity
import com.misa.fresher.databinding.ActivityAuthBinding
import com.misa.fresher.ui.main.MainActivity
import com.misa.fresher.utils.SharePreferenceHelper
import com.misa.fresher.utils.showToast

/**
 * - class's purpose:
 *
 * @author HTLong
 * @edit_at 22/03/2022
 */
class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate), AuthContract.View {

    private var presenter: AuthPresenter? = null
    private var isSignUp: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        initUI()
    }

    override fun initPresenter() {
        presenter = AuthPresenter().also { it.attach(this) }
    }

    private fun initUI() {
        binding.edtEmail.setText(SharePreferenceHelper.getString(baseContext, SharePreferenceHelper.KEY_LOGIN, ""))

        binding.btnSubmit.setOnClickListener {
            binding.layoutLoading.isVisible = true
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirm = binding.edtConfirmPassword.text.toString()

            if (isSignUp) presenter?.signUp(email, password, confirm)
            else presenter?.signIn(email, password)

        }
        binding.btnToggleSignUp.setOnClickListener { toggleSignUpUI() }
    }

    override fun toggleSignUpUI() {
        isSignUp = !isSignUp
        binding.run {
            if (isSignUp) {
                btnSubmit.text = getString(R.string.txt_sign_up)
                btnToggleSignUp.text = getString(R.string.txt_back_to_sign_in)
            } else {
                btnSubmit.text = getString(R.string.txt_sign_in)
                btnToggleSignUp.text = getString(R.string.txt_register_new_account)
            }
            edtPassword.setText("")
            edtConfirmPassword.setText("")
            fieldConfirmPassword.isVisible = isSignUp
        }
    }

    override fun notifyMessage(message: String?, default: Int?) {
        binding.layoutLoading.isVisible = false
        val text = message ?: default?.let { baseContext.getString(it) }
        text?.let { baseContext.showToast(text) }
    }

    override fun navToMainView(email: String) {
        SharePreferenceHelper.setString(baseContext, SharePreferenceHelper.KEY_LOGIN, email)
        startActivity(MainActivity.getIntent(context = baseContext))
    }

    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }
}