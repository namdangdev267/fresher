package com.misa.fresher.data.dao.selectedproduct

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.Bill
import com.misa.fresher.data.model.Products
import com.misa.fresher.data.model.SelectedProducts

class SelectedProductDao(private val dbHelper: AppDbHelper) : ISelectedProductDao {
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
                    cursor.getInt(cursor.getColumnIndex("amount")),
                    Products(cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("code")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getDouble(cursor.getColumnIndex("price")),
                        cursor.getInt(cursor.getColumnIndex("img")),
                        cursor.getString(cursor.getColumnIndex("color")),
                        cursor.getString(cursor.getColumnIndex("size"))
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