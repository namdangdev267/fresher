package com.misa.fresher.data.source.remote.response

/**
 * Lớp chứa thông tin trả về khi đăng kí
 *
 * @author Nguyễn Công Chính
 * @since 3/22/2022
 *
 * @version 1
 * @updated 3/22/2022: Tạo class
 */
data class SignUpResponse(
    val kind: String,
    val idToken: String,
    val email: String,
    val refreshToken: String,
    val expiresIn: String,
    val localId: String
) {
    companion object {
        const val CONFIRM_PASSWORD_FAILED = "CONFIRM_PASSWORD_FAILED"
        const val EMAIL_EXISTS = "EMAIL_EXISTS"
        const val INVALID_EMAIL = "INVALID_EMAIL"
    }
}
