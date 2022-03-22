package com.misa.fresher.data.repository

import com.misa.fresher.data.IOnLoadedCallback
import com.misa.fresher.data.model.User
import com.misa.fresher.data.model.UserError
import com.misa.fresher.data.source.user.IUserDataSource


/**
 * - class's purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-21
 */
class UserRepository private constructor(private val userRemoteDataSource: IUserDataSource.Remote) : IUserRepository {
    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userRemoteDataSource: IUserDataSource.Remote): UserRepository {
            if (instance == null) instance = UserRepository(userRemoteDataSource)
            return instance!!
        }
    }


    override fun signIn(username: String, password: String, callback: IOnLoadedCallback<User, UserError>) {
        userRemoteDataSource.signIn(username, password, callback)
    }

    override fun signUp(username: String, password: String, callback: IOnLoadedCallback<User, UserError>) {
        userRemoteDataSource.signUp(username, password, callback)
    }
}