package com.misa.fresher.data.dao.customer

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

    override fun getCustomer(): MutableList<Customer> {
        val databaseRead = dbHelper.readableDatabase
        val cursor = databaseRead.query(
            Customer.TABLE_NAME, null, null,
            null, null, null, null
        )
        val list = mutableListOf<Customer>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(Customer(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        databaseRead.close()
        return list
    }

    override suspend fun getCustomerById(id: Int): Customer {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: CustomerDao? = null
        fun getInstance(dbHelper: AppDbHelper): CustomerDao =
            instance ?: CustomerDao(dbHelper).also { instance = it }
    }
}