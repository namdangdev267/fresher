package com.misa.fresher.data.dao.itemproduct

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.database.AppDatabase
import com.misa.fresher.models.ItemProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ItemProductDao(private val appDatabase: AppDatabase) : IItemProductDao {

    companion object {
        private var instance: ItemProductDao? = null
        fun getInstance(appDatabase: AppDatabase): ItemProductDao =
            instance ?: ItemProductDao(appDatabase).also { instance = it }
    }

    @SuppressLint("Range")
    override suspend fun getAllProducts(): MutableList<ItemProduct> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(
            ItemProduct.TABLE_NAME, null, null,
            null, null, null, null
        )

        val list = mutableListOf<ItemProduct>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(
                    ItemProduct(
                        cursor
                    )
                )
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return list
    }

    @SuppressLint("Range")
    override suspend fun getProductWithID(id: Int): ItemProduct? {
        val db = appDatabase.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${ItemProduct.TABLE_NAME} WHERE ${ItemProduct.ID} = $id",
            null
        )

        var itemProduct: ItemProduct? = null
        if (cursor.moveToFirst()) {
            itemProduct = ItemProduct(
                cursor
            )
        }
        cursor.close()
        db.close()
        return itemProduct
    }

    override suspend fun addProduct(itemProduct: ItemProduct): Long {
        val db = appDatabase.writableDatabase
        return db.insertWithOnConflict(
            ItemProduct.TABLE_NAME, null,
            itemProduct.getContentValues(), SQLiteDatabase.CONFLICT_REPLACE
        )
    }
}