package com.misa.fresher.data.source.local.dao

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.models.ItemBill
import com.misa.fresher.data.source.AppDatabaseHelper

class ItemBillDao(private val appDatabaseHelper: AppDatabaseHelper) : IItemBillDao {

    @SuppressLint("Range")
    override suspend fun getAllListBill(): MutableList<ItemBill> {
        val db = appDatabaseHelper.readableDatabase


        val cursor = db.query(ItemBill.TABLE_NAME, null, null, null, null, null, null)

        val list = mutableListOf<ItemBill>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(
                    ItemBill(cursor)
                )
                cursor.moveToNext()
            }
        }
        cursor.close()
//        db.close()
        return list
    }

    override suspend fun addListBill(product: ItemBill): Long {
        val db = appDatabaseHelper.writableDatabase
        return db.insertWithOnConflict(
            ItemBill.TABLE_NAME, null, product.getContentValues(), SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    @SuppressLint("Recycle", "Range")
    override suspend fun getBillID(): Int {
        val db = appDatabaseHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${ItemBill.TABLE_NAME} ORDER BY ${ItemBill.ID} DESC LIMIT 1", null
        )
        if (cursor.moveToFirst()) {
//            db.close()
            return cursor.getInt(cursor.getColumnIndex(ItemBill.ID))
        }
//        db.close()
        return 0
    }

    companion object {
        private var instance: ItemBillDao? = null
        fun getInstance(appDatabaseHelper: AppDatabaseHelper): ItemBillDao =
            instance ?: ItemBillDao(appDatabaseHelper).also { instance = it }
    }
}