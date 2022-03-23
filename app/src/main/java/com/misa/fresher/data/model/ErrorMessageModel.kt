package com.misa.fresher.data.model

/**
 * Lớp model của bản tin lỗi luân chuyển trong ứng dụng
 *
 * @author Nguyễn Công Chính
 * @since 3/23/2022
 *
 * @version 1
 * @updated 3/23/2022: Tạo class
 */
data class ErrorMessageModel(
    val code: Int,
    val message: String,
) {
    companion object {
        // Đối với exception sẽ đặt là LOCAL_ERROR
        const val LOCAL_ERROR = -1
    }
}
