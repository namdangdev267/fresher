package com.misa.fresher.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Calendar

/**
 * Lớp dữ liệu chứa thông tin mỗi loại nhỏ trong 1 sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 5
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Bổ sung 2 thuộc tính tên và mã sản phẩm
 * @updated 3/15/2022: Đổi tên thuộc tính quantity -> [quantityAvailable]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 */
@Parcelize
data class ProductItem(
    val id: Long,
    val color: ProductColor,
    val size: ProductSize,
    val unit: ProductUnit,
    val price: Double,
    val quantityAvailable: Int,
    val createdAt: Calendar,
    var name: String = "",
    var code: String = ""
) : Parcelable
