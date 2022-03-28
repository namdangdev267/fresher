package com.misa.fresher.data.cart

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.DatabaseHelper
import com.misa.fresher.model.Product

class ImplCartDAO(
    val context: Context,
    val mDB: SQLiteDatabase
) : ICartDAO {
    constructor(context: Context) : this(context, DatabaseHelper(context).writableDatabase)

    override suspend fun addItem(billID: Int, product: Product, quantity: Int): Boolean {
        val cv = ContentValues()
        cv.put("BILL_ID", billID)
        cv.put("PRODUCT_ID", product.productId)
        cv.put("QUANTITY", quantity)
        cv.put("PRICE", product.productPrice)
        val newID = mDB.insert("CART", null, cv)
        return newID > 0
    }


}