package com.misa.fresher.data.source.remote.response

/**
 * Lớp chứa thông tin trả về khi đăng nhập
 *
 * @author Nguyễn Công Chính
 * @since 3/22/2022
 *
 * @version 1
 * @updated 3/22/2022: Tạo class
 */
data class SignInResponse(
    val kind: String,
    val localId: String,
    val email: String,
    val displayName: String,
    val idToken: String,
    val registered: Boolean
) {
    companion object {
        const val INVALID_PASSWORD = "INVALID_PASSWORD"
        const val EMAIL_NOT_FOUND = "EMAIL_NOT_FOUND"
        const val INVALID_EMAIL = "INVALID_EMAIL"
    }
}