package com.misa.fresher.data.dao.product

import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.Products

class ProductDao(private val dbHelper: AppDbHelper) : IProductDao {

    override suspend fun getAllProducts(): MutableList<Products> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            Products.TABLE_NAME, null, null,
            null, null, null, null
        )
        val list = mutableListOf<Products>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(Products(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return list
    }

    override fun addProducts(products: Products): Long {
        val db = dbHelper.writableDatabase
        return db.insertWithOnConflict(
            Products.TABLE_NAME, null,
            products.getContentValues(), SQLiteDatabase.CONFLICT_REPLACE
        ).also { db.close() }
    }

    companion object {
        private var instance: ProductDao? = null
        fun getInstance(dbHelper: AppDbHelper): ProductDao =
            instance ?: ProductDao(dbHelper).also { instance = it }
    }

}