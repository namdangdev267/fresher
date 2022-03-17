package com.misa.fresher.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Lớp dữ liệu chứa màu sắc của sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 4
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Kế thừa từ lớp [com.misa.fresher.data.entity.ProductType]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 */
@Parcelize
data class ProductColor(
    override val id: Long,
    override val name: String,
    val code: String,
) : ProductType(id, name), Parcelable
