package com.misa.fresher.data.repositories

import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.dao.itemproduct.ItemProductDao
import com.misa.fresher.models.ItemProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProductRepository(private val itemProductDao: ItemProductDao) {
    suspend fun getProducts():MutableList<ItemProduct>
    {
        return itemProductDao.getAllProducts()
    }

    suspend fun addProduct(itemProduct: ItemProduct) {
        itemProductDao.addProduct(itemProduct)
    }

    suspend fun getProductWithID(id: Int): ItemProduct? {
        return itemProductDao.getProductWithID(id)
    }
}