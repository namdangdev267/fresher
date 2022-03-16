package com.misa.fresher.data.entity

import java.io.Serializable

/**
 * Lớp thực thể chứa thông tin 1 sản phẩm trong giỏ hàng, đơn hàng,...
 *
 * @author Nguyễn Công Chính
 * @since 3/12/2022
 *
 * @version 4
 * @updated 3/12/2022: Tạo class
 * @updated 3/12/2022: Đổi tên có suffix là Model
 * @updated 3/15/2022: Đổi tên thành [ProductItemBill], chuyển nhà sang package [com.misa.fresher.data.entity]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 */
data class ProductItemBill(
    val item: ProductItem,
    var quantity: Int
) : Serializable
