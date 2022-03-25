package com.misa.fresher.data.source.local.dao

import com.misa.fresher.core.BaseDAO
import com.misa.fresher.data.entity.ProductSize
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng ProductSize
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductSizeDAO : BaseDAO<ProductSize> {

    companion object {
        private var dao: ProductSizeDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: ProductSizeDAOImpl(appDatabase).also { dao = it }
    }
}