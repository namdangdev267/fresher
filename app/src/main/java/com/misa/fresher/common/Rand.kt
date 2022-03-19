package com.misa.fresher.common

import java.util.*

/**
 * Lớp chứa các hàm chuyên dùng để random
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 2
 * @updated 3/14/2022: Tạo class
 * @updated 3/18/2022: Không sử dụng hàm generateBillCode() nữa, thay thế bằng [FakeData.getId]
 */
object Rand {

    val instance = Random()

    /**
     * Hàm sinh thời gian ngẫu nhiên từ khoảng 30 ngày trước đến hiện tại
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 2
     * @updated 3/10/2022: Tạo function
     * @updated 3/15/2022: Đổi nhà mới từ [FakeData] -> [Rand]
     */
    fun generateTime(): Date {
        val now = Date()
        val randomDay = instance.nextInt(60)
        now.time -= randomDay * 86400000L
        return now
    }
}