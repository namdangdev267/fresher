package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductDAOImpl(
    private val appDatabase: AppDatabase
) : ProductDAO {

    private val categoryDAO = CategoryDAO.getInstance(appDatabase)
    private val itemDAO = ProductItemDAO.getInstance(appDatabase)

    override fun create(list: List<Product>): Boolean {
        TODO("Not yet implemented")
    }

    override fun create(t: Product): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(): List<Product> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Product.TABLE_NAME).call()
        val list = mutableListOf<Product>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(Product(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        val result = mutableListOf<Product>()
        list.forEach {
            categoryDAO.getById(it.category.id)?.let { category ->
                result.add(
                    Product(
                        it.id,
                        it.name,
                        it.code,
                        category,
                        itemDAO.getByProductId(it.id)
                    )
                )
            }

        }

        return result
    }

    override fun getById(id: Long): Product? {
        TODO("Not yet implemented")
    }

    override fun update(t: Product): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(t: Product): Boolean {
        TODO("Not yet implemented")
    }
}