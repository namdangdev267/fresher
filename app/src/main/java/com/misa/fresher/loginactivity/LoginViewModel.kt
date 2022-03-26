package com.misa.fresher.loginactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var _loginMode = MutableLiveData<LoginMode>()
    val loginMode: LiveData<LoginMode>
        get() = _loginMode

    init {
        _loginMode.postValue(LoginMode.LOGIN)
    }

    fun changeLoginMode() {
        if (_loginMode.value == LoginMode.LOGIN) {
            _loginMode.postValue(LoginMode.SIGNUP)
        } else {
            _loginMode.postValue(LoginMode.LOGIN)
        }
    }
}