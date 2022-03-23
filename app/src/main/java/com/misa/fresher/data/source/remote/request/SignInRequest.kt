package com.misa.fresher.data.source.remote.request

/**
 * Lớp chứa thông tin cần gửi khi đăng nhập/đăng kí
 *
 * @author Nguyễn Công Chính
 * @since 3/23/2022
 *
 * @version 1
 * @updated 3/23/2022: Tạo class
 */
data class SignInRequest(
    val email: String,
    val password: String
)