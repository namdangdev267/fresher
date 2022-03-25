package com.misa.fresher.data.source.local.dao

import com.misa.fresher.core.BaseDAO
import com.misa.fresher.data.entity.ProductItem
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng ProductItem
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductItemDAO : BaseDAO<ProductItem> {

    fun getByProductId(productId: Long): List<ProductItem>

    companion object {
        private var dao: ProductItemDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: ProductItemDAOImpl(appDatabase).also { dao = it }
    }
}