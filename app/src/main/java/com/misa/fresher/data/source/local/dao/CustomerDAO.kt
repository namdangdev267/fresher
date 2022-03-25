package com.misa.fresher.data.source.local.dao

import com.misa.fresher.core.BaseDAO
import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng Customer
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface CustomerDAO : BaseDAO<Customer> {

    companion object {
        private var dao: CustomerDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: CustomerDAOImpl(appDatabase).also { dao = it }
    }
}