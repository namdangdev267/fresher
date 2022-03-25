package com.misa.fresher.data.source

import com.misa.fresher.data.entity.Bill
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy thông tin hóa đơn
 *
 * @author Nguyễn Công Chính
 * @since 3/25/2022
 *
 * @version 1
 * @updated 3/25/2022: Tạo class
 */
interface BillDataSource {

    interface Local {
        fun create(bill: Bill, action: LoadedAction<Boolean>)
        fun getAll(action: LoadedAction<List<Bill>>)
        fun getMaxId(action: LoadedAction<Long>)
    }

    interface Remote
}