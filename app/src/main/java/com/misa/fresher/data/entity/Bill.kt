package com.misa.fresher.data.entity

import java.util.*

/**
 * Lớp thực thể chứa thông tin đơn hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
data class Bill(
    val id: Long,
    val customer: Customer,
    val items: List<ProductItemBill>,
    val createdAt: Calendar
)
