package com.misa.fresher.data.entity

import java.io.Serializable

/**
 * Lớp thực thể chứa thông tin khách hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 1
 * @updated 3/14/2022: Tạo class
 */
data class Customer(
    val id: Long,
    val name: String,
    val tel: String,
    val address: String
) : Serializable {
    override fun toString(): String = "$name ($tel)"
}
