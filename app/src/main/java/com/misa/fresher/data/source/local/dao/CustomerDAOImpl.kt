package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class CustomerDAOImpl(
    private val appDatabase: AppDatabase
) : CustomerDAO {

    override fun create(list: List<Customer>): Boolean {
        TODO("Not yet implemented")
    }

    override fun create(t: Customer): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(): List<Customer> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Customer.TABLE_NAME).call()
        val list = mutableListOf<Customer>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(Customer(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        // Thông thường sau khi truy vấn sẽ đóng db, khi cần kiểm tra db hãy comment out dòng phía dưới
        // Hàm này được khởi chạy tại Login Presenter, sau khi nhận kết quả sẽ không làm gì vì vậy nó vô hại
        db.close()
        return list
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getById(id: Long): Customer? {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Customer.TABLE_NAME)
            .setSelection("${Customer.ID}=?")
            .setSelectionArgs(arrayOf(id.toString()))
            .call()
        var result: Customer? = null
        if (cursor.moveToFirst()) {
            result = Customer(cursor)
        }
        cursor.close()
        db.close()
        return result
    }

    override fun update(t: Customer): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(t: Customer): Boolean {
        TODO("Not yet implemented")
    }
}