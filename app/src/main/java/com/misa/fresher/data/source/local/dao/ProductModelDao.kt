package com.misa.fresher.data.source.local.dao

import android.database.Cursor
import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.data.source.local.database.AppDbHelper

class ProductModelDao private constructor(dbHelper: AppDbHelper)
    : BaseDao<ProductModel>(dbHelper), IProductDao.IModel<ProductModel> {

    override val tableName: String = ProductModel.TABLE_NAME
    override fun fromCursor(cursor: Cursor): ProductModel? = ProductModel.fromCursor(cursor)


    override fun update(row: ProductModel): Long {
        TODO()
    }

    override fun delete(row: ProductModel): Long {
        TODO()
    }

    companion object {
        private var instance: ProductModelDao? = null
        fun getInstance(dbHelper: AppDbHelper) = instance ?: ProductModelDao(dbHelper).also { instance = it }
    }


}