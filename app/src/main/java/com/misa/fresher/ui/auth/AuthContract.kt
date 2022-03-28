package com.misa.fresher.ui.auth

import com.misa.fresher.base.IBaseContract

/**
 * - class's purpose:
 *
 * @author HTLong
 * @edit_at 22/03/2022
 */

class AuthContract {
    interface View : IBaseContract.View {
        fun notifyMessage(message: String? = null, default: Int? = null)
        fun navToMainView(email: String)
        fun toggleSignUpUI()
    }

    interface Presenter : IBaseContract.Presenter<View> {
        fun signIn(email: String, password: String)
        fun signUp(email: String, password: String, confirm: String)
    }
}