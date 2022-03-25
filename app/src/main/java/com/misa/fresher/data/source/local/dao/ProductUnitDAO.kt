package com.misa.fresher.data.source.local.dao

import com.misa.fresher.core.BaseDAO
import com.misa.fresher.data.entity.ProductUnit
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng ProductUnit
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductUnitDAO : BaseDAO<ProductUnit> {

    companion object {
        private var dao: ProductUnitDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: ProductUnitDAOImpl(appDatabase).also { dao = it }
    }
}