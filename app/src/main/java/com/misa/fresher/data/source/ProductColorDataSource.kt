package com.misa.fresher.data.source

import com.misa.fresher.data.entity.ProductColor
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy màu sắc của sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductColorDataSource {

    interface Local {
        fun create(list: List<ProductColor>, action: LoadedAction<Boolean>)
        fun create(color: ProductColor, action: LoadedAction<Boolean>)
    }

    interface Remote
}