package com.misa.fresher.data.repository.user

import com.misa.fresher.data.IOnLoadedCallback
import com.misa.fresher.data.model.user.UserResponse
import com.misa.fresher.data.model.user.UserError

/**
 * - interface's purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-21
 */
interface IUserRepository {
    fun signIn(username: String, password: String, callback: IOnLoadedCallback<UserResponse, UserError>)
    fun signUp(username: String, password: String, callback: IOnLoadedCallback<UserResponse, UserError>)
}