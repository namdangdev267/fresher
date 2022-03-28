package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.Category
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class CategoryDAOImpl(
    private val appDatabase: AppDatabase
) : CategoryDAO {

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(): List<Category> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Category.TABLE_NAME).call()
        val list = mutableListOf<Category>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(Category(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return list
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getById(id: Long): Category? {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Category.TABLE_NAME)
            .setSelection("${Category.ID} = ?")
            .setSelectionArgs(arrayOf(id.toString()))
            .call()
        var result: Category? = null
        if (cursor.moveToFirst()) {
            result = Category(cursor)
        }
        cursor.close()
        db.close()
        return result
    }
}