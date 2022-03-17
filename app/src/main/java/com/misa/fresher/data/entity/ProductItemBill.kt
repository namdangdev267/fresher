package com.misa.fresher.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Lớp thực thể chứa thông tin 1 sản phẩm trong giỏ hàng, đơn hàng,...
 *
 * @author Nguyễn Công Chính
 * @since 3/12/2022
 *
 * @version 5
 * @updated 3/12/2022: Tạo class
 * @updated 3/12/2022: Đổi tên có suffix là Model
 * @updated 3/15/2022: Đổi tên thành [ProductItemBill], chuyển nhà sang package [com.misa.fresher.data.entity]
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 */
@Parcelize
data class ProductItemBill(
    val item: ProductItem,
    var quantity: Int
) : Parcelable
