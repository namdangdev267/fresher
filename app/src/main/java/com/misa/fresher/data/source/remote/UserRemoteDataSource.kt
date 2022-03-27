package com.misa.fresher.data.source.remote

import com.misa.fresher.data.source.UserDataSource
import com.misa.fresher.data.source.remote.api.UserApi
import com.misa.fresher.data.source.remote.request.SignInRequest
import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.LoadedAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRemoteDataSource: UserDataSource.Remote {

    private val userApi = UserApi.getInstance()

    override fun signIn(email: String, password: String, action: LoadedAction<SignInResponse>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = SignInRequest(email, password)
                val response = userApi.signIn(request)
                withContext(Dispatchers.Main) {
                    action.onResponse(response)
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    action.onException(ex)
                }
            }
        }
    }

    override fun signUp(email: String, password: String, action: LoadedAction<SignUpResponse>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = SignInRequest(email, password)
                val response = userApi.signUp(request)
                withContext(Dispatchers.Main) {
                    action.onResponse(response)
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    action.onException(ex)
                }
            }
        }
    }
}