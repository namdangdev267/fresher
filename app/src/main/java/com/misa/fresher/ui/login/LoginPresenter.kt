package com.misa.fresher.ui.login

import com.misa.fresher.data.model.User
import com.misa.fresher.data.retrofit.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    override fun login(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = Api.apiClient.signIn(user)
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main){
                        view?.loginSuccess()
                    }
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