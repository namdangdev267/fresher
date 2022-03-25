package com.misa.fresher.data.source.local.dao

import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.entity.ProductUnit
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductUnitDAOImpl(
    private val appDatabase: AppDatabase
) : ProductUnitDAO {

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<ProductUnit>): Boolean {
        val db = appDatabase.writableDatabase
        db.beginTransaction()

        var isSuccess = true
        for (item in list) {
            val values = item.getContentValues()
            if (db.insertWithOnConflict(
                    ProductUnit.TABLE_NAME,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE) <= 0) {
                isSuccess = false
                break
            }
        }
        if (isSuccess) {
            db.setTransactionSuccessful()
        }
        db.endTransaction()
        db.close()
        return isSuccess
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(t: ProductUnit): Boolean {
        val db = appDatabase.writableDatabase
        val values = t.getContentValues()
        return db.insertWithOnConflict(
            ProductUnit.TABLE_NAME,
            null,
            values,
            SQLiteDatabase.CONFLICT_REPLACE
        ).also { db.close() } > 0
    }

    override fun getAll(): List<ProductUnit> {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getById(id: Long): ProductUnit? {
        val db = appDatabase.readableDatabase
        val cursor = db.query(ProductUnit.TABLE_NAME)
            .setSelection("${ProductUnit.ID} = ?")
            .setSelectionArgs(arrayOf(id.toString()))
            .call()
        var result: ProductUnit? = null
        if (cursor.moveToFirst()) {
            result = ProductUnit(cursor)
        }
        cursor.close()
        db.close()
        return result
    }

    override fun update(t: ProductUnit): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(t: ProductUnit): Boolean {
        TODO("Not yet implemented")
    }
}