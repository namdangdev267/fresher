package com.misa.fresher.data.model

import com.misa.fresher.data.entity.ProductItem

/**
 * Lớp model chứa thông tin 1 sản phẩm trong giỏ hàng, đơn hàng,...
 *
 * @author Nguyễn Công Chính
 * @since 3/12/2022
 *
 * @version 2
 * @updated 3/12/2022: Tạo class
 * @updated 3/12/2022: Đổi tên có suffix là Model
 */
data class CartItemModel(
    val item: ProductItem,
    var quantity: Int
)
