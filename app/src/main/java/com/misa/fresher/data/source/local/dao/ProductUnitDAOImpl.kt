package com.misa.fresher.data.source.local.dao

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
}