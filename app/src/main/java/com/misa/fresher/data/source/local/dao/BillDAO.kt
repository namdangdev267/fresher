package com.misa.fresher.data.source.local.dao

import com.misa.fresher.core.BaseDAO
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng Bill
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface BillDAO : BaseDAO<Bill> {

    /**
     * Hàm lấy id lớn nhất trong bảng Bill
     *
     * @author Nguyễn Công Chính
     * @since 3/25/2022
     *
     * @version 1
     * @updated 3/25/2022: Tạo function
     */
    fun getMaxId(): Long

    companion object {
        private var dao: BillDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: BillDAOImpl(appDatabase).also { dao = it }
    }
}