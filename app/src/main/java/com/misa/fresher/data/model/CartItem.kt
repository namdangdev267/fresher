package com.misa.fresher.data.model

import com.misa.fresher.data.entity.ProductItem

/**
 * Lớp thực thể chứa thông tin 1 sản phẩm trong giỏ hàng, đơn hàng,...
 *
 * @author Nguyễn Công Chính
 * @since 3/12/2022
 *
 * @version 1
 * @updated 3/12/2022: Tạo class
 */
data class CartItem(
    val item: ProductItem,
    var quantity: Int
)
