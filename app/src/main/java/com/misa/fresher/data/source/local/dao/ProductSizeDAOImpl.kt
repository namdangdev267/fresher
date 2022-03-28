package com.misa.fresher.data.source.local.dao

import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.entity.ProductSize
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductSizeDAOImpl(
    private val appDatabase: AppDatabase
) : ProductSizeDAO {

    override fun create(list: List<ProductSize>): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(t: ProductSize): Boolean {
        val db = appDatabase.writableDatabase
        val values = t.getContentValues()
        return db.insertWithOnConflict(
            ProductSize.TABLE_NAME,
            null,
            values,
            SQLiteDatabase.CONFLICT_REPLACE
        ).also { db.close() } > 0
    }

    override fun getAll(): List<ProductSize> {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getById(id: Long): ProductSize? {
        val db = appDatabase.readableDatabase
        val cursor = db.query(ProductSize.TABLE_NAME)
            .setSelection("${ProductSize.ID} = ?")
            .setSelectionArgs(arrayOf(id.toString()))
            .call()
        var result: ProductSize? = null
        if (cursor.moveToFirst()) {
            result = ProductSize(cursor)
        }
        cursor.close()
        db.close()
        return result
    }

    override fun update(t: ProductSize): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(t: ProductSize): Boolean {
        TODO("Not yet implemented")
    }
}