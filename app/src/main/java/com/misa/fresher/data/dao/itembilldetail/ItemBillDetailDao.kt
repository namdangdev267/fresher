package com.misa.fresher.data.dao.itembilldetail

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.dao.itembill.IItemBillDao
import com.misa.fresher.data.database.AppDatabase
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.models.ItemProduct

class ItemBillDetailDao(private val appDatabase: AppDatabase) : IItemBillDetailDao {
    companion object {
        private var instance: ItemBillDetailDao? = null
        fun getInstance(appDatabase: AppDatabase): ItemBillDetailDao =
            instance ?: ItemBillDetailDao(appDatabase).also { instance = it }
    }

    @SuppressLint("Range")
    override suspend fun getAllBillDetails(): MutableList<ItemBillDetail> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(
            ItemBillDetail.TABLE_NAME, null, null,
            null, null, null, null
        )

        val list = mutableListOf<ItemBillDetail>()
        if (cursor.moveToFirst()) {

            while (!cursor.isAfterLast) {
                list.add(
                    ItemBillDetail(
                        null,
                        cursor.getString(cursor.getColumnIndex(ItemBillDetail.ID_ITEM_BILL)),
                        cursor.getInt(cursor.getColumnIndex(ItemBillDetail.QUANTITY))
                    )
                )
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return list
    }

    override suspend fun addBillDetail(itemBillDetail: ItemBillDetail): Long {
        val db = appDatabase.writableDatabase
        return db.insertWithOnConflict(
            ItemBillDetail.TABLE_NAME, null,
            itemBillDetail.getContentValues(), SQLiteDatabase.CONFLICT_REPLACE
        )
            .also { db.close() }
    }

    @SuppressLint("Range")
    override suspend fun getItemBillDetailWithBillId(billId: String): MutableList<ItemBillDetail> {
        val db = appDatabase.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${ItemBillDetail.TABLE_NAME} bd , ${ItemProduct.TABLE_NAME} p  " +
                    "WHERE ${ItemBillDetail.ID_ITEM_BILL} = $billId  " +
                    "and bd.${ItemBillDetail.ID_ITEM_PRODUCT} = p.${ItemProduct.ID}", null
        )

        val list = mutableListOf<ItemBillDetail>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(
                    ItemBillDetail(
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
}