package com.misa.fresher.data.source.local.dao

import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.entity.ProductItem
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.guard
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductItemDAOImpl(
    private val appDatabase: AppDatabase
) : ProductItemDAO {

    private val colorDAO = ProductColorDAO.getInstance(appDatabase)
    private val sizeDAO = ProductSizeDAO.getInstance(appDatabase)
    private val unitDAO = ProductUnitDAO.getInstance(appDatabase)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<ProductItem>): Boolean {
        val db = appDatabase.writableDatabase
        db.beginTransaction()

        var isSuccess = true
        for (item in list) {
            val values = item.getContentValues()
            if (db.insertWithOnConflict(
                    ProductItem.TABLE_NAME,
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

    override fun create(t: ProductItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<ProductItem> {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getById(id: Long): ProductItem? {
        val db = appDatabase.readableDatabase
        val cursor = db.query(ProductItem.TABLE_NAME)
            .setSelection("${ProductItem.ID} = ?")
            .setSelectionArgs(arrayOf(id.toString()))
            .call()
        var result: ProductItem? = null
        if (cursor.moveToFirst()) {
            result = ProductItem(cursor)
        }
        cursor.close()
        db.close()

        result?.let {
            guard(
                colorDAO.getById(it.color.id),
                sizeDAO.getById(it.size.id),
                unitDAO.getById(it.unit.id)
            ) { color, size, unit ->
                result = ProductItem(
                    it.id,
                    color,
                    size,
                    unit,
                    it.price,
                    it.quantityAvailable,
                    it.createdAt,
                    it.name,
                    it.code
                )
            }
        }

        return result
    }

    override fun update(t: ProductItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(t: ProductItem): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getByProductId(productId: Long): List<ProductItem> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(ProductItem.TABLE_NAME)
            .setSelection("${ProductItem.PRODUCT_ID} = ?")
            .setSelectionArgs(arrayOf(productId.toString()))
            .call()
        val list = mutableListOf<ProductItem>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(ProductItem(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        val result = mutableListOf<ProductItem>()
        list.forEach {
            guard(
                colorDAO.getById(it.color.id),
                sizeDAO.getById(it.size.id),
                unitDAO.getById(it.unit.id)
            ) { color, size, unit ->
                result.add(ProductItem(
                    it.id,
                    color,
                    size,
                    unit,
                    it.price,
                    it.quantityAvailable,
                    it.createdAt,
                    it.name,
                    it.code
                ))
            }
        }

        return result
    }
}