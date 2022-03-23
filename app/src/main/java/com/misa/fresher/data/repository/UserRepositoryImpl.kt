package com.misa.fresher.data.repository

import com.misa.fresher.data.source.UserDataSource
import com.misa.fresher.data.source.remote.UserRemoteDataSource
import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.LoadedAction

class UserRepositoryImpl : UserRepository {

    private val userRemote: UserDataSource.Remote = UserRemoteDataSource()

    override fun signIn(email: String, password: String, action: LoadedAction<SignInResponse>) {
        return userRemote.signIn(email, password, action)
    }

    override fun signUp(email: String, password: String, action: LoadedAction<SignUpResponse>) {
        return userRemote.signUp(email, password, action)
    }
}