package com.misa.fresher.data.source.local.dao

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.models.Product
import com.misa.fresher.data.source.AppDatabaseHelper

/**
 * Mục địch sử dụng:
 * Sử dụng khi:
 * @Author Truong Trung Kien
 * @date: 3/24/2022 10:15 PM
 **/
class ProductDao(private val appDatabase: AppDatabaseHelper) : IProductDao {

    @SuppressLint("Recycle", "Range")
    override suspend fun getAllProducts(): MutableList<Product> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Product.TABLE_NAME, null, null, null, null, null, null)
        val list = mutableListOf<Product>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(Product(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
//        db.close()
        return list
    }

    override suspend fun addProduct(product: Product): Long {
        val db = appDatabase.writableDatabase
        return db.insertWithOnConflict(
            Product.TABLE_NAME,
            null,
            product.getContentValues(),
            SQLiteDatabase.CONFLICT_REPLACE
        )
//            .also { db.close() }
    }

    @SuppressLint("Recycle")
    override suspend fun getProductID(id: Int): Product? {
        val db = appDatabase.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${Product.TABLE_NAME} WHERE ${Product.IDPRODUCT} = $id",
            null
        )
        var product: Product? = null
        if (cursor.moveToFirst()) {
            product = Product(cursor)
        }
        cursor.close()
//        db.close()
        return product
    }
}
