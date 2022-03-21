package com.misa.fresher.ui.login

import com.misa.fresher.model.User
import com.misa.fresher.retrofit.ApiClient
import com.misa.fresher.retrofit.api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    override fun login(user: User) {
        val signInApi = api.getInstance().create(ApiClient::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = signInApi.signIn(user)
                if (response.isSuccessful && response.body() != null) {
                    view?.loginSuccess()
                } else {
                    withContext(Dispatchers.Main) {
                        view?.showErrorMessage(response.errorBody().toString())
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view?.showErrorMessage(e.printStackTrace().toString())
                }
            }
        }
    }

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}