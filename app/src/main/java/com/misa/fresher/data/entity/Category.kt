package com.misa.fresher.data.entity

import java.io.Serializable

/**
 * Lớp dữ liệu chứa loại sản phẩm. VD: áo, quần, giày,...
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 2
 * @updated 3/9/2022: Tạo class
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 */
data class Category(
    val id: Long,
    val name: String,
) : Serializable
