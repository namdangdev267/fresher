package com.misa.fresher.ui.login

import com.misa.fresher.base.BaseContract
import com.misa.fresher.model.User

class LoginContract {
    interface View : BaseContract.View{
        fun loginSuccess()
    }
    interface Presenter : BaseContract.Presenter<View>{
        fun login(user : User)
    }
}