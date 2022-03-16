package com.misa.fresher.data.entity

/**
 * Lớp dữ liệu chứa thông tin của 1 sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/9/2022
 *
 * @version 2
 * @updated 3/9/2022: Tạo class
 * @updated 3/12/2022: Cài đặt tên, mã cho các item mỗi khi khởi tạo đối tượng.
 * Thêm constructor(Product, List<ProductItem>) để sử dụng cho chức năng lọc.
 */
data class Product(
    val id: Long,
    val name: String,
    val code: String,
    val category: Category,
    val items: List<ProductItem>,
) {
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
