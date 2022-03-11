package com.misa.fresher.data.entity

/**
 * Lớp thực thể chứa thông tin chung giữa các lớp màu sắc, kích thước, đơn vị...
 *
 * @author Nguyễn Công Chính
 * @since 3/11/2022
 *
 * @version 1
 * @updated 3/11/2022: Tạo class
 */
open class ProductType(
    open val id: Long,
    open val name: String,
)