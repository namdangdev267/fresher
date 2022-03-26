package com.misa.fresher.data.product

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.DatabaseHelper
import com.misa.fresher.model.Product

class ImplProductDAO(
    val context: Context,
    val mDB: SQLiteDatabase
) : IProductDAO {
    constructor(context: Context) : this(context, DatabaseHelper(context).writableDatabase)

    @SuppressLint("Range")
    override suspend fun selectAllProduct(): ArrayList<Product> {
        val sql = "select * from PRODUCT"
        val c = mDB.rawQuery(sql, null)
        val mList = ArrayList<Product>()
        if (c.moveToFirst()) {
            while (!c.isAfterLast) {
                val id = c.getInt(c.getColumnIndex("ID_PRODUCT"))
                val name = c.getString(c.getColumnIndex("PRODUCT_NAME"))
                val sku = c.getString(c.getColumnIndex("PRODUCT_SKU"))
                val color = c.getString(c.getColumnIndex("COLOR"))
                val size = c.getString(c.getColumnIndex("SIZE"))
                val price = c.getDouble(c.getColumnIndex("PRODUCT_PRICE"))
                val product = Product(id, name, sku, price.toInt(), color, size)
                mList.add(product)
                c.moveToNext()
            }
        }
        c.close()
        mDB.close()
        return mList
    }

    override suspend fun addProduct(product: Product): Boolean {
        val cv=ContentValues()
            cv.put("PRODUCT_NAME",product.productName)
            cv.put("PRODUCT_SKU",product.productSKU)
            cv.put("COLOR",product.color)
            cv.put("SIZE",product.size)
            cv.put("PRODUCT_PRICE",product.productPrice)
        val insert=mDB.insertWithOnConflict("PRODUCT",null,cv,SQLiteDatabase.CONFLICT_REPLACE)
        return insert>0
    }

}