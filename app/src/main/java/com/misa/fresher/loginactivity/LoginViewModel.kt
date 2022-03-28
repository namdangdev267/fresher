package com.misa.fresher.loginactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var mLogin = MutableLiveData<LoginMode>()
    val login: LiveData<LoginMode>
        get() = mLogin

    init {
        mLogin.postValue(LoginMode.LOGIN)
    }

    fun changeLoginMode() {
        if (mLogin.value == LoginMode.LOGIN) {
            mLogin.postValue(LoginMode.SIGNUP)
        } else {
            mLogin.postValue(LoginMode.LOGIN)
        }
    }
}