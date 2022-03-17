package com.misa.fresher.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Lớp thực thể chứa thông tin khách hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 2
 * @updated 3/14/2022: Tạo class
 * @updated 3/17/2022: Thay serialize bằng parcelable
 */
@Parcelize
data class Customer(
    val id: Long,
    val name: String,
    val tel: String,
    val address: String
) : Parcelable {
    override fun toString(): String = "$name ($tel)"
}
