package com.misa.fresher.data.dao.bill

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.dao.product.ProductDao
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.Bill
import com.misa.fresher.data.model.Customer
import com.misa.fresher.utils.Queries.QUERY_BILL
import com.misa.fresher.utils.Queries.SELECT_LASTEST_BILL_ID

class BillDao(private val dbHelper: AppDbHelper) : IBillDao {
    override suspend fun addBill(bill: Bill): Long {
        val db = dbHelper.writableDatabase
        return db.insertWithOnConflict(
            Bill.TABLE_NAME, null,
            bill.getContentValues(), SQLiteDatabase.CONFLICT_REPLACE
        ).also { db.close() }
    }

    @SuppressLint("Range")
    override suspend fun getAllBill(): MutableList<Bill> {
        val databaseRead = dbHelper.readableDatabase
        val cursor = databaseRead.rawQuery(
            "SELECT bill.id,customer.id AS id_cus ,customer.name AS name_cus,customer.number AS number_cus," +
                    "customer.address AS address_cus,bill.date,bill.totalPrice " +
                    "FROM bill LEFT JOIN customer " +
                    "ON bill.idCustomer = customer.id", null
        )
        val list = mutableListOf<Bill>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(
                    Bill(
                        cursor.getInt(cursor.getColumnIndex(Bill.ID)),
                        null,
                        Customer(
                            cursor.getInt(cursor.getColumnIndex("id_cus")),
                            cursor.getString(cursor.getColumnIndex("name_cus")),
                            cursor.getString(cursor.getColumnIndex("number_cus")),
                            cursor.getString(cursor.getColumnIndex("address_cus"))
                        ),
                        cursor.getString(cursor.getColumnIndex(Bill.DATE)),
                        cursor.getDouble(cursor.getColumnIndex(Bill.TOTAL_PRICE))
                    )
                )
                cursor.moveToNext()
            }
        }
        cursor.close()
        databaseRead.close()
        return list
    }

    @SuppressLint("Range")
    override suspend fun getLastestBillID(): Int {
        val databaseRead = dbHelper.readableDatabase
        val cursor = databaseRead.rawQuery(
            SELECT_LASTEST_BILL_ID,
            null
        )
        if (cursor.moveToFirst()) {
            databaseRead.close()
            return cursor.getInt(cursor.getColumnIndex(Bill.ID))
        }
        databaseRead.close()
        return 0
    }

    companion object {
        private var instance: BillDao? = null
        fun getInstance(dbHelper: AppDbHelper): BillDao =
            instance ?: BillDao(dbHelper).also { instance = it }
    }
}