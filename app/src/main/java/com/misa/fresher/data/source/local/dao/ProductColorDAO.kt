package com.misa.fresher.data.source.local.dao

import com.misa.fresher.core.BaseDAO
import com.misa.fresher.data.entity.ProductColor
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng ProductColor
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductColorDAO : BaseDAO<ProductColor> {

    companion object {
        private var dao: ProductColorDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: ProductColorDAOImpl(appDatabase).also { dao = it }
    }
}