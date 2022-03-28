package com.misa.fresher.data.source.local.dao

import android.database.Cursor
import com.misa.fresher.data.model.product.ProductItem
import com.misa.fresher.data.source.local.database.AppDbHelper

class ProductItemDao private constructor(dbHelper: AppDbHelper)
    : BaseDao<ProductItem>(dbHelper), IProductDao.IItem<ProductItem> {
    override val tableName: String = ProductItem.TABLE_NAME
    override fun fromCursor(cursor: Cursor): ProductItem? = ProductItem.fromCursor(cursor)

    override fun update(row: ProductItem): Long {
        TODO("Not yet implemented")
    }

    override fun delete(row: ProductItem): Long {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: ProductItemDao? = null
        fun getInstance(dbHelper: AppDbHelper) = instance ?: ProductItemDao(dbHelper).also { instance = it }
    }
}