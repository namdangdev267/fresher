package com.misa.fresher.data.bill

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.DatabaseHelper
import com.misa.fresher.model.BillInfor

class ImplBillDAO(
    val context: Context,
    val mDB: SQLiteDatabase
) : IBillDAO {
    constructor(context: Context) : this(context, DatabaseHelper(context).writableDatabase)

    override suspend fun addBill(total: Int): Boolean {
        val cv = ContentValues()
        cv.put("TOTAL", total)
        val newID = mDB.insert("BILL", null, cv)
        //mDB.close()
        return newID > 0
    }

    @SuppressLint("Range")
    override suspend fun selectAllBill(): ArrayList<BillInfor> {
        val sql = "select * from BILL"
        val cursor = mDB.rawQuery(sql, null)
        val mList = ArrayList<BillInfor>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(cursor.getColumnIndex("BILL_ID"))
                val total = cursor.getInt(cursor.getColumnIndex("TOTAL"))
                val bill = BillInfor(id, null, null, total)
                mList.add(bill)
                cursor.moveToNext()
            }

        }
        cursor.close()
        mDB.close()
        return mList
    }

    @SuppressLint("Range")
    override suspend fun getBillID(): Int {
        var id = 0
        val cursor = mDB.rawQuery("SELECT * FROM BILL ORDER BY BILL_ID DESC LIMIT 1", null)
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex("BILL_ID"))
        }
        cursor.close()
        mDB.close()
        return id
    }

    override suspend fun updateBill(total: Int, billID: Int): Boolean {
        val ctV = ContentValues()
        ctV.put("TOTAL", total)
        val c = mDB.update("BILL", ctV, "BILL_ID=$billID", null)
        return c > 0
    }

    @SuppressLint("Range")
    override suspend fun getToTalByBillID(billID: Int): Int {
        val sql = "SELECT SUM(QUANTITY*PRICE) as 'TOTAL' FROM CART WHERE BILL_ID=$billID"
        val cursor = mDB.rawQuery(sql, null)
        var total = 0
        while (cursor.moveToFirst()) {
            total = cursor.getInt(cursor.getColumnIndex("TOTAL"))
        }
        cursor.close()
        mDB.close()
        return total
    }

    override suspend fun selectProductByBiLLId(billId: Int) {
        // TODO: 3/25/2022  
    }

}