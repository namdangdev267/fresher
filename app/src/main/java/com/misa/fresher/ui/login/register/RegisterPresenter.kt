package com.misa.fresher.ui.login.register

import com.misa.fresher.data.model.User
import com.misa.fresher.data.retrofit.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterPresenter : RegisterContract.Presenter {
    private var view: RegisterContract.View? = null
    override fun register(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = Api.apiClient.signIn(user)
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main){
                        view?.registerSuccess()
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

    override fun attach(view: RegisterContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

}