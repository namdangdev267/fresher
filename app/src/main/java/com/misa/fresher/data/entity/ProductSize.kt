package com.misa.fresher.data.entity

import kotlinx.parcelize.Parcelize

/**
 * Lớp dữ liệu chứa kích thước sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 5
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Kế thừa từ lớp [com.misa.fresher.data.entity.ProductType]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 * @updated 3/18/2022: Vì [ProductType] đã impl Parcelable nên [ProductSize] không cần phải làm việc đó nữa
 */
@Parcelize
data class ProductSize(
    override val id: Long,
    override val name: String,
    val code: String,
) : ProductType(id, name)
