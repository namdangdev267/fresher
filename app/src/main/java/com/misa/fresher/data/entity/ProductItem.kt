package com.misa.fresher.data.entity

import java.util.Calendar

/**
 * Lớp dữ liệu chứa thông tin mỗi loại nhỏ trong 1 sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 2
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Bổ sung 2 thuộc tính tên và mã sản phẩm
 */
data class ProductItem(
    val id: Long,
    val color: ProductColor,
    val size: ProductSize,
    val unit: ProductUnit,
    val price: Double,
    val quantity: Int,
    val createdAt: Calendar,
) {
    var name: String = ""
    var code: String = ""
}
