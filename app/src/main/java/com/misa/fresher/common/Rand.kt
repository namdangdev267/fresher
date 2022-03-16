package com.misa.fresher.common

import java.util.*

/**
 * Lớp chứa các hàm chuyên dùng để random
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 1
 * @updated 3/14/2022: Tạo class
 */
object Rand {

    val instance = Random()

    /**
     * Sinh mã đơn hàng ngẫu nhiên
     *
     * @author Nguyễn Công Chính
     * @since 3/14/2022
     *
     * @version 1
     * @updated 3/14/2022: Tạo function
     */
    fun generateBillCode(): Int = instance.nextInt(1000000000)

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
    fun generateTime(): Calendar {
        val now = Calendar.getInstance()
        val randomDay = instance.nextInt(60)
        now.timeInMillis -= randomDay * 86400000L
        return now
    }
}