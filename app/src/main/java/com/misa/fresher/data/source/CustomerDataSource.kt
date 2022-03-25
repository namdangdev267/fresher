package com.misa.fresher.data.source

import com.misa.fresher.data.entity.Customer
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy thông tin khách hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface CustomerDataSource {

    interface Local {
        fun create(list: List<Customer>, action: LoadedAction<Boolean>)
        fun getAll(action: LoadedAction<List<Customer>>)
    }

    interface Remote
}