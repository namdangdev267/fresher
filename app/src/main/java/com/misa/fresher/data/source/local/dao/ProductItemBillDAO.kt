package com.misa.fresher.data.source.local.dao

import com.misa.fresher.core.BaseDAO
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng ProductItemBill
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductItemBillDAO : BaseDAO<ProductItemBill> {

    fun getByBillId(billId: Long): List<ProductItemBill>

    companion object {
        private var dao: ProductItemBillDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: ProductItemBillDAOImpl(appDatabase).also { dao = it }
    }
}