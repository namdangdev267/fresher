package kma.longhoang.beta.dao

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import kma.longhoang.beta.database.AppDatabase
import kma.longhoang.beta.model.BillModel

class BillDAO private constructor(appDatabase: AppDatabase) {
    private val database: SQLiteDatabase = appDatabase.writableDatabase

    fun getAllBill(): MutableList<BillModel> {
        val cursor: Cursor = database.rawQuery("SELECT * FROM tb_bill", null)
        val list = mutableListOf<BillModel>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(BillModel(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list
    }


    fun addBill(bill: BillModel): Boolean {
        val value = bill.getContentValues()
        return database.insert("tb_bill", null, value) > 0
    }

    @SuppressLint("Range")
    fun getBillId(): Int {
        var id = 0
        val cursor: Cursor =
            database.rawQuery("SELECT * FROM tb_bill ORDER BY id DESC LIMIT 1", null)
        return if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex("id"))
            id.plus(1)
        } else {
            id.plus(1)
        }
    }

    companion object {
        private var instance: BillDAO? = null
        fun getInstance(appDatabase: AppDatabase): BillDAO? {
            if (null == instance) {
                instance = BillDAO(appDatabase)
            }
            return instance
        }
    }
}