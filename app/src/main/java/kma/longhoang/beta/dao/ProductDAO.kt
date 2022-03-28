package kma.longhoang.beta.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import kma.longhoang.beta.database.AppDatabase
import kma.longhoang.beta.model.ProductModel


class ProductDAO private constructor(appDatabase: AppDatabase) {
    private var database: SQLiteDatabase = appDatabase.writableDatabase


    @SuppressLint("Recycle")
    fun getAllProduct(): MutableList<ProductModel> {
        val cursor: Cursor = database.rawQuery("SELECT * FROM tb_product", null)
        val list = mutableListOf<ProductModel>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(ProductModel(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list
    }

    @SuppressLint("Recycle")
    fun getProductId(id: Int): ProductModel {
        val selection = "id =?"
        val selectionArg = arrayOf<String>(id.toString())
        val cursor: Cursor =
            database.query("tb_product", null, selection, selectionArg, null, null, null)
        cursor.moveToFirst()
        val product = ProductModel(cursor)
        cursor.close()
        return product
    }

    companion object {
        private var instance: ProductDAO? = null
        fun getInstance(appDatabase: AppDatabase): ProductDAO? {
            if (null == instance) {
                instance = ProductDAO(appDatabase)
            }
            return instance
        }
    }
}