package com.misa.fresher.data.source.local.dao

import android.database.Cursor
import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.data.source.local.database.AppDbHelper

class ProductBillDao private constructor(dbHelper: AppDbHelper) : BaseDao<ProductBill>(dbHelper){
    override val tableName: String = ProductBill.TABLE_NAME
    override fun fromCursor(cursor: Cursor): ProductBill? = ProductBill.fromCursor(cursor)

    override fun update(row: ProductBill): Long {
        TODO("Not yet implemented")
    }

    override fun delete(row: ProductBill): Long {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: ProductBillDao? = null
        fun getInstance(dbHelper: AppDbHelper) = instance ?: ProductBillDao(dbHelper).also { instance = it }
    }
}