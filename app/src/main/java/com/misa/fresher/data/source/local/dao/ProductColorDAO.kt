package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.ProductColor
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng ProductColor
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 2
 * @updated 3/24/2022: Tạo class
 * @updated 3/28/2022: Không kế thừa từ BaseDAO nữa, tự thân vận động
 */
interface ProductColorDAO {

    fun getById(id: Long): ProductColor?

    companion object {
        private var dao: ProductColorDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: ProductColorDAOImpl(appDatabase).also { dao = it }
    }
}