package com.misa.fresher.data.repository

import com.misa.fresher.data.entity.Product
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
interface ProductRepository {
    fun create(list: List<Product>, action: LoadedAction<Boolean>)
    fun getAll(action: LoadedAction<List<Product>>)
}