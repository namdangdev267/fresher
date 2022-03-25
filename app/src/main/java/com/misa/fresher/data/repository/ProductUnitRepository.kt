package com.misa.fresher.data.repository

import com.misa.fresher.data.entity.ProductUnit
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
interface ProductUnitRepository {
    fun create(list: List<ProductUnit>, action: LoadedAction<Boolean>)
    fun create(unit: ProductUnit, action: LoadedAction<Boolean>)
}