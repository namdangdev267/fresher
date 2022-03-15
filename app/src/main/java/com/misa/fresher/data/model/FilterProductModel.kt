package com.misa.fresher.data.model

import com.misa.fresher.data.entity.Category
import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.entity.ProductItem
import com.misa.fresher.util.enum.ProductSortType
import java.text.Collator
import java.util.*

/**
 * Lớp model chứa thông tin về các thể loại lọc sản phẩm, sử dụng trong màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/12/2022
 *
 * @version 1
 * @updated 3/12/2022: Tạo class
 */
class FilterProductModel {
    var keyword = ""
    var isQuantityMoreThanZero = false
    var selectedCategory: Category? = null
    var sortBy = ProductSortType.NAME

    /**
     * Hàm lọc ra các sản phẩm dựa vào thông số ở trên
     *
     * @author Nguyễn Công Chính
     * @since 3/12/2022
     *
     * @version 1
     * @updated 3/12/2022: Tạo function
     */
    fun filter(input: List<Product>): List<Product> {
        var result = input

        // Lọc theo tên hoặc mã sản phẩm
        result = result.filter {
            it.name.contains(keyword, true) || it.code.contains(keyword, true)
        }

        // Lọc số lương lớn hơn 0
        if (isQuantityMoreThanZero) {
            result = result.mapNotNull { product ->
                val filterItems: List<ProductItem> = product.items.filter { it.quantityAvailable > 0 }
                if (filterItems.isNotEmpty()) {
                    Product(product, filterItems)
                } else {
                    null
                }
            }
        }

        // Lọc theo danh mục sản phẩm
        selectedCategory?.let { category ->
            result = result.filter { it.category == category }
        }

        // Sắp xếp
        result = when (sortBy) {
            ProductSortType.NAME -> result.sortedWith { p1, p2 ->
                Collator.getInstance(Locale("vi", "VN")).compare(p1.name, p2.name)
            }
            ProductSortType.NEW_PRODUCT -> result.sortedByDescending { product ->
                product.items.maxOf { it.createdAt.timeInMillis }
            }
            else -> result.sortedByDescending { product ->
                product.items.sumOf { it.quantityAvailable }
            }
        }

        return result
    }
}