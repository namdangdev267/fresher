package com.misa.fresher.data.repository

import com.misa.fresher.data.entity.ProductColor
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
interface ProductColorRepository {
    fun create(list: List<ProductColor>, action: LoadedAction<Boolean>)
    fun create(color: ProductColor, action: LoadedAction<Boolean>)
}