package com.misa.fresher.common

import java.util.*

/**
 * Lớp chứa các hàm chuyên dùng để random
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 3
 * @updated 3/14/2022: Tạo class
 * @updated 3/18/2022: Không sử dụng hàm generateBillCode() nữa, thay thế bằng [FakeData.getId]
 * @updated 3/23/2022: Chuyển lớp Random -> [RandomSingleton]
 */
object RandomSingleton {

    private val random = Random()

    fun getInstance(): Random = random

    /**
     * Hàm sinh thời gian ngẫu nhiên từ khoảng 30 ngày trước đến hiện tại
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 2
     * @updated 3/10/2022: Tạo function
     * @updated 3/15/2022: Đổi nhà mới từ [FakeData] -> [RandomSingleton]
     */
    fun generateTime(): Date {
        val now = Date()
        val randomDay = getInstance().nextInt(60)
        now.time -= randomDay * 86400000L
        return now
    }
}