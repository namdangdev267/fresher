package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng Product
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 2
 * @updated 3/24/2022: Tạo class
 * @updated 3/28/2022: Không kế thừa từ BaseDAO nữa, tự thân vận động
 */
interface ProductDAO {

    fun getAll(): List<Product>

    companion object {
        private var dao: ProductDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: ProductDAOImpl(appDatabase).also { dao = it }
    }
}