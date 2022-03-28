package com.misa.fresher.data.source.local.dao

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
}