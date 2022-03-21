package com.misa.fresher.ui.login.register

import com.misa.fresher.model.User
import com.misa.fresher.retrofit.ApiClient
import com.misa.fresher.retrofit.api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterPresenter : RegisterContract.Presenter {
    private var view: RegisterContract.View? = null
    override fun register(user: User) {
        val signInApi = api.getInstance().create(ApiClient::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = signInApi.signUp(user)
                if (response.isSuccessful && response.body() != null) {
                    view?.registerSuccess()
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

    override fun attach(view: RegisterContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

}