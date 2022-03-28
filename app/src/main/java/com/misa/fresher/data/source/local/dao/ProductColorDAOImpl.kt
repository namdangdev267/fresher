package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.ProductColor
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductColorDAOImpl(
    private val appDatabase: AppDatabase
) : ProductColorDAO {

    override fun create(list: List<ProductColor>): Boolean {
        TODO("Not yet implemented")
    }

    override fun create(t: ProductColor): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<ProductColor> {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getById(id: Long): ProductColor? {
        val db = appDatabase.readableDatabase
        val cursor = db.query(ProductColor.TABLE_NAME)
            .setSelection("${ProductColor.ID} = ?")
            .setSelectionArgs(arrayOf(id.toString()))
            .call()
        var result: ProductColor? = null
        if (cursor.moveToFirst()) {
            result = ProductColor(cursor)
        }
        cursor.close()
        db.close()
        return result
    }

    override fun update(t: ProductColor): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(t: ProductColor): Boolean {
        TODO("Not yet implemented")
    }
}