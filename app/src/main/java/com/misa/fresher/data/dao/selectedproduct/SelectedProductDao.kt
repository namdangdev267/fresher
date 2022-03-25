package com.misa.fresher.data.dao.selectedproduct

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.Bill
import com.misa.fresher.data.model.Products
import com.misa.fresher.data.model.SelectedProducts

class SelectedProductDao(private val dbHelper: AppDbHelper) : ISelectedProductDao {
    override fun addSelectedProduct(selectedProducts: SelectedProducts, billId: Int): Long {
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

    override fun getSelectedProDuctByBill(billId: Int): MutableList<SelectedProducts> {
        val db = dbHelper.readableDatabase
//        val cursor = db.rawQuery(
//            "SELECT SP.${SelectedProducts.ID},SP.${SelectedProducts.ID_BILL},SP.${SelectedProducts.ID_PRODUCT},SP.${SelectedProducts.AMONUT}" +
//                    "FROM ${SelectedProducts.TABLE_NAME} SP,${Bill.TABLE_NAME} B, ${Products.TABLE_NAME} P" +
//                    "WHERE SP.${SelectedProducts.ID} = B.${Bill.ID} AND SP.${SelectedProducts.ID_PRODUCT} = P.${Products.ID}",null
//        )
        TODO()
    }

    companion object {
        private var instance: SelectedProductDao? = null
        fun getInstance(dbHelper: AppDbHelper): SelectedProductDao =
            instance ?: SelectedProductDao(dbHelper).also { instance = it }
    }
}