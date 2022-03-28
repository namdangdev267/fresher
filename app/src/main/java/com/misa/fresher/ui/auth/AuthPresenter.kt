package com.misa.fresher.ui.auth

import com.misa.fresher.R
import com.misa.fresher.data.IOnLoadedCallback
import com.misa.fresher.data.model.user.UserResponse
import com.misa.fresher.data.model.user.UserError
import com.misa.fresher.di.Injector

/**
 * - Class's purpose:
 *
 * @author HTLong
 * @edit_at
 */

class AuthPresenter : AuthContract.Presenter {
    private var view: AuthContract.View? = null
    private val userRepo by lazy { Injector.getUserRepository() }

    override fun signIn(email: String, password: String) {
        if (validate(email, password)) {
            userRepo.signIn(email, password, object : IOnLoadedCallback<UserResponse, UserError> {
                override fun onSuccess(data: UserResponse) {
                    view?.navToMainView(email)
                }

                override fun onFailure(error: UserError) {
                    view?.notifyMessage(error.message)
                }

                override fun onException(e: Exception) {
                    e.printStackTrace()
                    view?.notifyMessage(e.localizedMessage, R.string.txt_something_wrong)
                }
            })
        }
    }

    override fun signUp(email: String, password: String, confirm: String) {
        if (validate(email, password, confirm)) {
            userRepo.signUp(email, password, object : IOnLoadedCallback<UserResponse, UserError> {
                override fun onSuccess(data: UserResponse) {
                    view?.toggleSignUpUI()
                    view?.notifyMessage(default = R.string.txt_sign_up_success)
                }

                override fun onFailure(error: UserError) {
                    view?.notifyMessage(error.message)
                }

                override fun onException(e: Exception) {
                    e.printStackTrace()
                    view?.notifyMessage(e.localizedMessage, R.string.txt_something_wrong)
                }
            })
        }
    }

    private fun validate(email: String, password: String, confirm: String? = null): Boolean {
        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.notifyMessage(default = R.string.txt_invalid_email)
            return false
        } else if (password.isBlank() || password.length < 6) {
            view?.notifyMessage(default = R.string.txt_invalid_password)
            return false
        } else if (confirm != null && password != confirm) {
            view?.notifyMessage(default = R.string.txt_invalid_confirm_password)
            return false
        }

        return true
    }

    override fun attach(view: AuthContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}