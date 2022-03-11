package com.misa.fresher.data.entity

/**
 * Lớp dữ liệu chứa thông tin của 1 sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 1
 * @updated 3/9/2022: Tạo class
 */
data class Product(
    val id: Long,
    val name: String,
    val code: String,
    val category: Category,
    val items: List<ProductItem>,
)
