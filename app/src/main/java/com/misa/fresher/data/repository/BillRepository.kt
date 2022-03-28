package com.misa.fresher.data.repository

import com.misa.fresher.data.entity.Bill
import com.misa.fresher.util.LoadedAction

/**
 * Lớp repository để điều hướng nguồn dữ liệu (remote/local/...)
 *
 * @author Nguyễn Công Chính
 * @since 3/25/2022
 *
 * @version 1
 * @updated 3/25/2022: Tạo class
 */
interface BillRepository {
    fun create(bill: Bill, action: LoadedAction<Boolean>)
    fun getAll(action: LoadedAction<List<Bill>>)
    fun getMaxId(action: LoadedAction<Long>)
}