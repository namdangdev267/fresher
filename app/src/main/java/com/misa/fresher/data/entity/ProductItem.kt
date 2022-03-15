package com.misa.fresher.data.entity

import java.util.Calendar

/**
 * Lớp dữ liệu chứa thông tin mỗi loại nhỏ trong 1 sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 3
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Bổ sung 2 thuộc tính tên và mã sản phẩm
 * @updated 3/15/2022: Đổi tên thuộc tính quantity -> [quantityAvailable]
 */
data class ProductItem(
    val id: Long,
    val color: ProductColor,
    val size: ProductSize,
    val unit: ProductUnit,
    val price: Double,
    val quantityAvailable: Int,
    val createdAt: Calendar,
) {
    var name: String = ""
    var code: String = ""
}
