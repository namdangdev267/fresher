package com.misa.fresher.ui.login.register

import com.misa.fresher.base.BaseContract
import com.misa.fresher.data.model.User

class RegisterContract :BaseContract {
    interface View : BaseContract.View{
        fun registerSuccess()
    }
    interface Presenter: BaseContract.Presenter<View>{
        fun register(user: User)
    }
}