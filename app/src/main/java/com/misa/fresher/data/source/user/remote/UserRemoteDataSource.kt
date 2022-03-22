package com.misa.fresher.data.source.user.remote

import com.google.gson.Gson
import com.misa.fresher.data.IOnLoadedCallback
import com.misa.fresher.data.model.User
import com.misa.fresher.data.model.UserError
import com.misa.fresher.data.source.user.IUserDataSource
import com.misa.fresher.data.source.user.remote.api.UserClient
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception

class UserRemoteDataSource private constructor() : IUserDataSource.Remote {

    override fun signIn(username: String, password: String, callback: IOnLoadedCallback<User, UserError>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val res = UserClient.instance.signIn(username, password)
                withContext(Dispatchers.Main) { handle(res, callback) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { callback.onException(e) }
            }
        }
    }

    override fun signUp(username: String, password: String, callback: IOnLoadedCallback<User, UserError>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val res = UserClient.instance.signUp(username, password)
                withContext(Dispatchers.Main) { handle(res, callback) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { callback.onException(e) }
            }
        }
    }

    private fun handle(res: Response<String>, callback: IOnLoadedCallback<User, UserError>) {
        val body = res.body() ?: res.errorBody()?.string()
        if (body != null) {
            val user = Gson().fromJson(body, User::class.java)
            if (!res.isSuccessful && user.error != null) {
                callback.onFailure(user.error)
            } else callback.onSuccess(user)
        } else callback.onFailure(UserError(code = res.code(), message = res.message()))
    }

    companion object {
        private var instance: UserRemoteDataSource? = null
        fun getInstance(): UserRemoteDataSource {
            if (instance == null) {
                instance = UserRemoteDataSource()
            }
            return instance!!
        }
    }
}