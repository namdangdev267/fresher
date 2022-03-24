package com.misa.fresher.data.dao.customer

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.Customer

class CustomerDao(private val dbHelper: AppDbHelper) : ICustomerDao {
    override fun addCustomer(customer: Customer): Long {
        val databaseWrite = dbHelper.writableDatabase
        return databaseWrite.insertWithOnConflict(
            Customer.TABLE_NAME, null,
            customer.getContentValues(), SQLiteDatabase.CONFLICT_REPLACE
        ).also { databaseWrite.close() }
    }

    @SuppressLint("Range")
    override fun getCustomer(): MutableList<Customer> {
        val databaseRead = dbHelper.readableDatabase
        val cursor = databaseRead.query(
            Customer.TABLE_NAME, null, null,
            null, null, null, null
        )
        val list = mutableListOf<Customer>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(
                    Customer(
                        cursor.getInt(cursor.getColumnIndex(Customer.ID)),
                        cursor.getString(cursor.getColumnIndex(Customer.NAME)),
                        cursor.getString(cursor.getColumnIndex(Customer.NUMBER)),
                        cursor.getString(cursor.getColumnIndex(Customer.ADDRESS)),
                    )
                )
                cursor.moveToNext()
            }
        }
        cursor.close()
        databaseRead.close()
        return list
    }
    companion object {
        private var instance: CustomerDao? = null
        fun getInstance(dbHelper: AppDbHelper): CustomerDao =
            instance ?: CustomerDao(dbHelper).also { instance = it }
    }
}