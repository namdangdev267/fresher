package com.misa.fresher.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Lớp thực thể chứa thông tin đơn hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 4
 * @updated 3/15/2022: Tạo class
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 * @updated 3/18/2022: Thay trường [createdAt] từ Calendar -> Date
 */
@Parcelize
data class Bill(
    val id: Long,
    val customer: Customer,
    val items: List<ProductItemBill>,
    val createdAt: Date
) : Parcelable
