package com.misa.fresher.data.repository

import com.misa.fresher.data.entity.ProductSize
import com.misa.fresher.util.LoadedAction

/**
 * Lớp repository để điều hướng nguồn dữ liệu (remote/local/...)
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductSizeRepository {
    fun create(list: List<ProductSize>, action: LoadedAction<Boolean>)
    fun create(size: ProductSize, action: LoadedAction<Boolean>)
}