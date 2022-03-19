package com.misa.fresher.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Lớp dữ liệu chứa thông tin của 1 sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 4
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Cài đặt tên, mã cho các item mỗi khi khởi tạo đối tượng.
 * Thêm constructor(Product, List<ProductItem>) để sử dụng cho chức năng lọc.
 * @updated 3/16/2022: Fix lỗi serialize, khi truyền qua bundle
 * @updated 3/17/2022: Thay serialize bằng parcelable
 */
@Parcelize
data class Product(
    val id: Long,
    val name: String,
    val code: String,
    val category: Category,
    val items: List<ProductItem>,
) : Parcelable {
    init {
        items.forEach {
            it.name = "$name (${it.color.name}/${it.size.name})"
            it.code = "$code-${it.color.code}-${it.size.code}"
        }
    }

    /**
     * Constructor khởi tạo 1 product mới với các thông tin từ product cũ, chỉ có items thay đổi.
     *
     * Sử dụng trong chức năng lọc sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/12/2022
     *
     * @version 1
     * @updated 3/12/2022: Tạo function
     */
    constructor(product: Product, newItems: List<ProductItem>) : this(
        product.id,
        product.name,
        product.code,
        product.category,
        newItems
    )
}
