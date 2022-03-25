package kma.longhoang.beta.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import kma.longhoang.beta.database.AppDatabase
import kma.longhoang.beta.model.CustomerModel

class CustomerDAO private constructor(appDatabase: AppDatabase) {
    private val database: SQLiteDatabase = appDatabase.writableDatabase

    @SuppressLint("Recycle")
    fun getAllCustomer(): MutableList<CustomerModel> {
        val cursor: Cursor = database.rawQuery("SELECT * FROM tb_customer", null)
        val list = mutableListOf<CustomerModel>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(CustomerModel(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list
    }

    @SuppressLint("Recycle", "Range")
    fun getCustomerById(id: Int): CustomerModel? {
        val cursor: Cursor = database.rawQuery("SELECT * FROM tb_customer WHERE id = $id", null)
        if (cursor.moveToFirst()) {
            val customer = CustomerModel()
            customer.id = cursor.getInt(cursor.getColumnIndex("id"))
            customer.name = cursor.getString(cursor.getColumnIndex("name"))
            customer.phone = cursor.getString(cursor.getColumnIndex("phone"))
            return customer
        }
        return null
    }


    @SuppressLint("Range", "Recycle")
    fun getCustomerId(phone: String?): Int? {
        return if (phone != null) {
            val cursor: Cursor =
                database.rawQuery("SELECT id FROM tb_customer WHERE phone = '$phone'", null)
            cursor.moveToFirst()
            return cursor.getInt(cursor.getColumnIndex("id"))
        } else {
            null
        }
    }

    companion object {
        private var instance: CustomerDAO? = null
        fun getInstance(appDatabase: AppDatabase): CustomerDAO? {
            if (null == instance) {
                instance = CustomerDAO(appDatabase)
            }
            return instance
        }
    }
}