package com.misa.fresher.ui.login

import com.misa.fresher.base.BaseContract
import com.misa.fresher.data.model.User

class LoginContract:BaseContract {
    interface View : BaseContract.View{
        fun loginSuccess()
    }
    interface Presenter : BaseContract.Presenter<View>{
        fun login(user : User)
    }
}