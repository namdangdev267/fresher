package com.misa.fresher.data.repository

import com.misa.fresher.data.entity.Customer
import com.misa.fresher.util.LoadedAction

/**
 * Lớp repository để điều hướng nguồn dữ liệu (remote/local/...)
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 2
 * @updated 3/24/2022: Tạo class
 * @updated 3/28/2022: Loại bỏ hàm createList do không có nhu cầu sử dụng
 */
interface CustomerRepository {
    fun getAll(action: LoadedAction<List<Customer>>)
}