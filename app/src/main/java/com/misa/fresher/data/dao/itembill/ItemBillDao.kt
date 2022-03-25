package com.misa.fresher.data.dao.itembill

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.dao.itemproduct.IItemProductDao
import com.misa.fresher.data.database.AppDatabase
import com.misa.fresher.models.InfoShip
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.ItemProduct
import java.util.*

class ItemBillDao(private val appDatabase: AppDatabase) : IItemBillDao {
    companion object {
        private var instance: ItemBillDao? = null
        fun getInstance(appDatabase: AppDatabase): ItemBillDao =
            instance ?: ItemBillDao(appDatabase).also { instance = it }
    }

    @SuppressLint("Range")
    override suspend fun getAllBills(): MutableList<ItemBill> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(
//            "SELECT * FROM ${ItemBill.TABLE_NAME} b LEFT JOIN ${InfoShip.TABLE_NAME} s ON b.${ItemBill.ID_INFO_SHIP}=s.${InfoShip.ID}",null)

        ItemBill.TABLE_NAME, null, null,
        null, null, null, null)

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
        db.close()
        return list
    }

    override suspend fun addBill(itemBill: ItemBill): Long {
        val db = appDatabase.writableDatabase
        return db.insertWithOnConflict(
            ItemBill.TABLE_NAME, null,
            itemBill.getContentValues(), SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    @SuppressLint("Range")
    override suspend fun getLatestIdItemBill(): Int {
        val databaseRead = appDatabase.readableDatabase
        val cursor = databaseRead.rawQuery(
            "SELECT * FROM ${ItemBill.TABLE_NAME} ORDER BY ${ItemBill.ID} DESC LIMIT 1",
            null
        )
        if (cursor.moveToFirst()) {
            databaseRead.close()
            return cursor.getInt(cursor.getColumnIndex(ItemBill.ID))
        }
        databaseRead.close()
        return 0
    }
}