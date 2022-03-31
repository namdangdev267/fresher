package com.misa.fresher.common

import java.util.*

/**
 * Lớp chứa các hàm chuyên dùng để random
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 4
 * @updated 3/14/2022: Tạo class
 * @updated 3/18/2022: Không sử dụng hàm generateBillCode() nữa, thay thế bằng FakeData.getId
 * @updated 3/23/2022: Chuyển lớp Random -> [RandomSingleton]
 * @updated 3/31/2022: Thêm 2 hàm sinh giá tiền và số lượng
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

    /**
     * Hàm sinh giá tiền ngẫu nhiên, [min], [max] nhập vào có đơn vị nghìn đồng
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    fun generatePrice(min: Int, max: Int, step: Int = 5): Double {
        val stepsAvail = (max - min) / step
        return (min + getInstance().nextInt(stepsAvail) * step).toDouble() * 1000
    }

    /**
     * Hàm sinh số lượng không ngẫu nhiên cho lắm, [min], [max] nhập vào có đơn vị nghìn đồng
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    fun generateQuantity(): Int =
        if (getInstance().nextInt(5) == 0) 0
        else getInstance().nextInt(100)
}