package com.misa.fresher.data.repository

import com.misa.fresher.data.source.remote.response.SignInResponse
import com.misa.fresher.data.source.remote.response.SignUpResponse
import com.misa.fresher.util.LoadedAction

/**
 * Lớp repository để điều hướng nguồn dữ liệu (remote/local/...)
 *
 * @author Nguyễn Công Chính
 * @since 3/22/2022
 *
 * @version 1
 * @updated 3/22/2022: Tạo class
 */
interface UserRepository {
    fun signIn(email: String, password: String, action: LoadedAction<SignInResponse>)
    fun signUp(email: String, password: String, action: LoadedAction<SignUpResponse>)
}