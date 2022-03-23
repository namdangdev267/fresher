package com.misa.fresher.ui.login

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.misa.fresher.R
import com.misa.fresher.core.BaseActivity
import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.databinding.ActivityLoginBinding
import com.misa.fresher.ui.main.MainActivity
import com.misa.fresher.util.toast

/**
 * Màn hình đăng kí/đăng nhập
 *
 * @author Nguyễn Công Chính
 * @since 3/23/2022
 *
 * @version 1
 * @updated 3/23/2022: Tạo class
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginContract.View, LoginPresenter>(), LoginContract.View {

    override val getInflater: (inflater: LayoutInflater) -> ActivityLoginBinding
        get() = ActivityLoginBinding::inflate
    override val initPresenter: () -> LoginPresenter
        get() = { LoginPresenter(this) }

    override fun initUI() {
        configOtherView()
    }

    /**
     * Hàm cài đặt các view khác
     *
     * @author Nguyễn Công Chính
     * @since 3/23/2022
     *
     * @version 1
     * @updated 3/23/2022: Tạo function
     */
    private fun configOtherView() {
        binding.btnAction.setOnClickListener {
            binding.btnAction.isEnabled = false
            presenter?.action(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString(),
                binding.etConfirmPassword.text.toString()
            )
        }
        binding.btnSignUp.setOnClickListener {
            presenter?.changeState(LoginPresenter.STATE_SIGN_UP)
        }
        binding.btnBack.setOnClickListener {
            presenter?.changeState(LoginPresenter.STATE_SIGN_IN)
        }
    }

    /**
     * Dọn dẹp các trường nhập mỗi khi đổi trạng thái
     *
     * @author Nguyễn Công Chính
     * @since 3/23/2022
     *
     * @version 1
     * @updated 3/23/2022: Tạo function
     */
    private fun clearInput() {
        binding.etShop.text?.clear()
        binding.etUsername.text?.clear()
        binding.etPassword.text?.clear()
        binding.etConfirmPassword.text?.clear()
    }

    override fun updateUI() {
        presenter?.changeState(LoginPresenter.STATE_SIGN_IN)
    }

    override fun toSignInState() {
        binding.tilConfirmPassword.visibility = View.GONE
        binding.btnAction.text = getString(R.string.sign_in)
        binding.btnSignUp.visibility = View.VISIBLE
        binding.btnForgotPassword.visibility = View.VISIBLE
        binding.btnBack.visibility = View.GONE
        clearInput()
    }

    override fun toSignUpState() {
        binding.tilConfirmPassword.visibility = View.VISIBLE
        binding.btnAction.text = getString(R.string.sign_up)
        binding.btnSignUp.visibility = View.GONE
        binding.btnForgotPassword.visibility = View.GONE
        binding.btnBack.visibility = View.VISIBLE
        clearInput()
    }

    override fun signInSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun signInFailure(error: String) {
        binding.btnAction.isEnabled = true
        when (error) {
            SignInResponse.EMAIL_NOT_FOUND -> toast(baseContext, getString(R.string.email_not_found))
            SignInResponse.INVALID_PASSWORD -> toast(baseContext, getString(R.string.invalid_password))
            SignInResponse.INVALID_EMAIL -> toast(baseContext, getString(R.string.invalid_email))
            else -> toast(baseContext, getString(R.string.unknown_error) + error)
        }
    }

    override fun signUpSuccess() {
        binding.btnAction.isEnabled = true
        toast(baseContext, getString(R.string.sign_up_success))
        presenter?.changeState(LoginPresenter.STATE_SIGN_IN)
    }

    override fun signUpFailure(error: String) {
        binding.btnAction.isEnabled = true
        when (error) {
            SignUpResponse.CONFIRM_PASSWORD_FAILED -> toast(baseContext, getString(R.string.confirm_password_failed))
            SignUpResponse.EMAIL_EXISTS -> toast(baseContext, getString(R.string.email_exists))
            SignUpResponse.INVALID_EMAIL -> toast(baseContext, getString(R.string.invalid_email))
            else -> toast(baseContext, getString(R.string.unknown_error) + error)
        }
    }
}