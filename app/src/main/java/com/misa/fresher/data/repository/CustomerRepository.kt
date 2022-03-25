package com.misa.fresher.data.repository

import com.misa.fresher.data.entity.Customer
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
interface CustomerRepository {
    fun create(list: List<Customer>, action: LoadedAction<Boolean>)
    fun getAll(action: LoadedAction<List<Customer>>)
}