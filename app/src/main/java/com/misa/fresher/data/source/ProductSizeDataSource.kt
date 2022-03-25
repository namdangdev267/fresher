package com.misa.fresher.data.source

import com.misa.fresher.data.entity.ProductSize
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy kích thước của sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductSizeDataSource {

    interface Local {
        fun create(list: List<ProductSize>, action: LoadedAction<Boolean>)
        fun create(size: ProductSize, action: LoadedAction<Boolean>)
    }

    interface Remote
}