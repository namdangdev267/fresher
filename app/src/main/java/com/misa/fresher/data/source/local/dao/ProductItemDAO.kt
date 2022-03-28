package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.ProductItem
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng ProductItem
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 2
 * @updated 3/24/2022: Tạo class
 * @updated 3/28/2022: Không kế thừa từ BaseDAO nữa, tự thân vận động
 */
interface ProductItemDAO {

    fun getById(id: Long): ProductItem?
    fun getByProductId(productId: Long): List<ProductItem>

    companion object {
        private var dao: ProductItemDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: ProductItemDAOImpl(appDatabase).also { dao = it }
    }
}