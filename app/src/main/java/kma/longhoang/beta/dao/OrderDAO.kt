package kma.longhoang.beta.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import kma.longhoang.beta.database.AppDatabase
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.model.ProductModel

class OrderDAO private constructor(appDatabase: AppDatabase) {
    private val database: SQLiteDatabase = appDatabase.writableDatabase

    @SuppressLint("Recycle")
    fun getAllOrder(): MutableList<OrderModel> {
        val cursor: Cursor = database.rawQuery("SELECT * FROM tb_order", null)
        val list = mutableListOf<OrderModel>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(OrderModel(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close();
        return list
    }

    @SuppressLint("Recycle")
    fun getOrderById(billId: Int): MutableList<OrderModel> {
        val cursor: Cursor =
            database.rawQuery("SELECT * FROM tb_order WHERE billId = $billId", null)
        val list = mutableListOf<OrderModel>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(OrderModel(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return list
    }

    fun addOrder(order: OrderModel, billId: Int): Boolean {
        val value = ContentValues()
        value.put("name", order.name)
        value.put("code", order.code)
        value.put("color", order.color?.name)
        value.put("price", order.price)
        value.put("amount", order.amount)
        value.put("billId", billId)
        return database.insert("tb_order", null, value) > 0
    }

    companion object {
        private var instance: OrderDAO? = null
        fun getInstance(appDatabase: AppDatabase): OrderDAO? {
            if (null == instance) {
                instance = OrderDAO(appDatabase)
            }
            return instance
        }
    }
}