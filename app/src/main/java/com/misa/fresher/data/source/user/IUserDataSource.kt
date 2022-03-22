package com.misa.fresher.data.source.user

import com.misa.fresher.data.IOnLoadedCallback
import com.misa.fresher.data.model.User
import com.misa.fresher.data.model.UserError

/**
 * - interface's purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-21
 */

interface IUserDataSource {
    class Local {

    }

    interface Remote {
        fun signIn(username: String, password: String, callback: IOnLoadedCallback<User, UserError>)
        fun signUp(username: String, password: String, callback: IOnLoadedCallback<User, UserError>)
    }
}