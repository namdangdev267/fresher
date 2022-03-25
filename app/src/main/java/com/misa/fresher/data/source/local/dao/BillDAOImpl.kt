package com.misa.fresher.data.source.local.dao

import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class BillDAOImpl(
    private val appDatabase: AppDatabase
) : BillDAO {

    private val customerDAO = CustomerDAO.getInstance(appDatabase)
    private val itemBillDAO = ProductItemBillDAO.getInstance(appDatabase)

    override fun create(list: List<Bill>): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(t: Bill): Boolean {
        val db = appDatabase.writableDatabase
        db.beginTransaction()

        // Insert 1 row trong bảng bill, r lại qua bảng ProductItemBill để insert các row chứa item
        var isSuccess = true
        val billValues = t.getContentValues()
        if (db.insertWithOnConflict(
                Bill.TABLE_NAME,
                null,
                billValues,
                SQLiteDatabase.CONFLICT_REPLACE
        ) > 0) {
            for (item in t.items) {
                val itemValues = item.getContentValues()
                itemValues.put(ProductItemBill.BILL_ID, t.id)
                if (db.insertWithOnConflict(
                        ProductItemBill.TABLE_NAME,
                        null,
                        itemValues,
                        SQLiteDatabase.CONFLICT_REPLACE) <= 0) {
                    isSuccess = false
                    break
                }
            }
        } else {
            isSuccess = false
        }
        if (isSuccess) {
            db.setTransactionSuccessful()
        }
        db.endTransaction()
        db.close()
        return isSuccess
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(): List<Bill> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Bill.TABLE_NAME).call()
        val list = mutableListOf<Bill>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(Bill(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        val result = mutableListOf<Bill>()
        list.forEach {
            customerDAO.getById(it.customer.id)?.let { customer ->
                result.add(Bill(
                    it.id,
                    customer,
                    itemBillDAO.getByBillId(it.id),
                    it.createdAt
                    ))
            }
        }

        return result
    }

    override fun getById(id: Long): Bill? {
        TODO("Not yet implemented")
    }

    override fun update(t: Bill): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(t: Bill): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getMaxId(): Long {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Bill.TABLE_NAME)
            .setOrderBy("${Bill.ID} DESC")
            .setLimit("1")
            .call()
        var maxId = 0L
        if (cursor.moveToFirst()) {
            maxId = cursor.getLong(cursor.getColumnIndexOrThrow(Bill.ID))
        }
        cursor.close()
        db.close()

        return maxId
    }
}