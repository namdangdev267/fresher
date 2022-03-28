package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng Customer
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 2
 * @updated 3/24/2022: Tạo class
 * @updated 3/28/2022: Không kế thừa từ BaseDAO nữa, tự thân vận động
 */
interface CustomerDAO {

    fun getAll(): List<Customer>
    fun getById(id: Long): Customer?

    companion object {
        private var dao: CustomerDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: CustomerDAOImpl(appDatabase).also { dao = it }
    }
}