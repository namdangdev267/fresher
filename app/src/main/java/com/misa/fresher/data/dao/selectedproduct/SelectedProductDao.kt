package com.misa.fresher.data.dao.selectedproduct

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.Bill
import com.misa.fresher.data.model.Products
import com.misa.fresher.data.model.SelectedProducts

class SelectedProductDao private constructor(private val dbHelper: AppDbHelper) : ISelectedProductDao {
    override suspend fun addSelectedProduct(selectedProducts: SelectedProducts, billId: Int): Long {
        val contentValues = ContentValues()
        val databaseWrite = dbHelper.writableDatabase
        contentValues.apply {
            put(SelectedProducts.ID_BILL, billId)
            put(SelectedProducts.ID_PRODUCT, selectedProducts.product.id)
            put(SelectedProducts.AMONUT, selectedProducts.amonut)
        }
        return databaseWrite.insertWithOnConflict(
            SelectedProducts.TABLE_NAME, null,
            contentValues, SQLiteDatabase.CONFLICT_REPLACE
        ).also { databaseWrite.close() }
    }

    @SuppressLint("Range")
    override suspend fun getSelectedProductByBill(billId: Int): MutableList<SelectedProducts> {
        val db = dbHelper.readableDatabase
        val list = mutableListOf<SelectedProducts>()
        val cursor = db.rawQuery("select s.amount,p.id,p.code,p.name,p.price,p.img,p.color,p.size \n" +
                "from selectedproduct s, bill b, product p\n" +
                "where s.idProduct = p.id\n" +
                "and s.idBill = b.id \n " +
                "and s.idBill = ${billId} ",null)
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast){
                list.add(SelectedProducts(
                    cursor.getInt(cursor.getColumnIndex(SelectedProducts.AMONUT)),
                    Products(cursor.getInt(cursor.getColumnIndex(SelectedProducts.ID_PRODUCT)),
                        cursor.getString(cursor.getColumnIndex(Products.CODE)),
                        cursor.getString(cursor.getColumnIndex(Products.NAME)),
                        cursor.getDouble(cursor.getColumnIndex(Products.PRICE)),
                        cursor.getInt(cursor.getColumnIndex(Products.IMG)),
                        cursor.getString(cursor.getColumnIndex(Products.COLOR)),
                        cursor.getString(cursor.getColumnIndex(Products.SIZE))
                    ))
                )
                cursor.moveToNext()
            }
        }
        return list
    }

    companion object {
        private var instance: SelectedProductDao? = null
        fun getInstance(dbHelper: AppDbHelper): SelectedProductDao =
            instance ?: SelectedProductDao(dbHelper).also { instance = it }
    }
}