package com.misa.fresher.ui.auth

import com.misa.fresher.base.BaseContract

/**
 * - class's purpose:
 *
 * @author HTLong
 * @edit_at 22/03/2022
 */

class AuthContract {
    interface View : BaseContract.View {
        fun notifyMessage(message: String? = null, default: Int? = null)
        fun navToMainView(email: String)
        fun toggleSignUpUI()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun signIn(email: String, password: String)
        fun signUp(email: String, password: String, confirm: String)
    }
}