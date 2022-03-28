package com.misa.fresher.ui.login

import android.content.Context
import com.misa.fresher.core.BasePresenter
import com.misa.fresher.data.source.remote.response.SignUpResponse

/**
 * Presenter cho màn hình đăng kí/đăng nhập
 *
 * @author Nguyễn Công Chính
 * @since 3/23/2022
 *
 * @version 1
 * @updated 3/23/2022: Tạo class
 */
class LoginPresenter(
    view: LoginContract.View,
    context: Context
) : BasePresenter<LoginContract.View>(view, context), LoginContract.Presenter {

    private var currentState = STATE_SIGN_IN

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun changeState(state: Int) {
        currentState = state
        when (currentState) {
            STATE_SIGN_IN -> view.toSignInState()
            else -> view.toSignUpState()
        }
    }

    /**
     * @version 1
     * @updated 3/23/2022: Override lần đầu
     */
    override fun action(email: String, password: String, confirmPassword: String) {
        when (currentState) {
            STATE_SIGN_IN -> {
                dataManager.signIn(email, password)
                    .onSuccess { view.signInSuccess() }
                    .onFailure { view.signInFailure(it.message) }
                    .call()
            }
            else -> {
                if (password.isEmpty() || password != confirmPassword) {
                    view.signUpFailure(SignUpResponse.CONFIRM_PASSWORD_FAILED)
                    return
                }
                dataManager.signUp(email, password)
                    .onSuccess { view.signUpSuccess() }
                    .onFailure { view.signUpFailure(it.message) }
                    .call()
            }
        }
    }

    companion object {
        const val STATE_SIGN_IN = 1
        const val STATE_SIGN_UP = 2
    }
}