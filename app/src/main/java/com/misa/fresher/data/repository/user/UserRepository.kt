package com.misa.fresher.data.repository.user

import com.misa.fresher.data.IOnLoadedCallback
import com.misa.fresher.data.model.user.UserResponse
import com.misa.fresher.data.model.user.UserError
import com.misa.fresher.data.source.IUserDataSource


/**
 * - class's purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-21
 */
class UserRepository private constructor(private val userRemoteDataSource: IUserDataSource.Remote) : IUserRepository {
    override fun signIn(username: String, password: String, callback: IOnLoadedCallback<UserResponse, UserError>) {
        userRemoteDataSource.signIn(username, password, callback)
    }

    override fun signUp(username: String, password: String, callback: IOnLoadedCallback<UserResponse, UserError>) {
        userRemoteDataSource.signUp(username, password, callback)
    }

    companion object {
        private var instance: UserRepository? = null
        fun getInstance(userRemoteDataSource: IUserDataSource.Remote) = instance ?: UserRepository(userRemoteDataSource).also { instance = it }
    }
}
