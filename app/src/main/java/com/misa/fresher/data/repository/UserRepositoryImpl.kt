package com.misa.fresher.data.repository

import android.content.Context
import com.misa.fresher.data.source.UserDataSource
import com.misa.fresher.data.source.remote.UserRemoteDataSource
import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.LoadedAction

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class UserRepositoryImpl(
    context: Context
) : UserRepository {

    private val userRemote: UserDataSource.Remote = UserRemoteDataSource()

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun signIn(email: String, password: String, action: LoadedAction<SignInResponse>) {
        return userRemote.signIn(email, password, action)
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun signUp(email: String, password: String, action: LoadedAction<SignUpResponse>) {
        return userRemote.signUp(email, password, action)
    }
}