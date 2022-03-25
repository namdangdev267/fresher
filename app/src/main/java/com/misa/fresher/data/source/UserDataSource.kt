package com.misa.fresher.data.source

import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy thông tin user
 *
 * @author Nguyễn Công Chính
 * @since 3/22/2022
 *
 * @version 1
 * @updated 3/22/2022: Tạo class
 */
interface UserDataSource {

    interface Local

    interface Remote {
        fun signIn(email: String, password: String, action: LoadedAction<SignInResponse>)
        fun signUp(email: String, password: String, action: LoadedAction<SignUpResponse>)
    }
}