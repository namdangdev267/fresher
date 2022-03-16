package com.misa.fresher.data.entity

import java.io.Serializable

/**
 * Lớp dữ liệu chứa đơn vị mua của sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 3
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Kế thừa từ lớp [com.misa.fresher.data.entity.ProductType]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 */
data class ProductUnit(
    override val id: Long,
    override val name: String,
) : ProductType(id, name), Serializable