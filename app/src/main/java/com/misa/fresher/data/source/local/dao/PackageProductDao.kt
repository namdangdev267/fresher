package com.misa.fresher.data.source.local.dao

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.models.PackageProduct
import com.misa.fresher.data.models.Product
import com.misa.fresher.data.source.AppDatabaseHelper

class PackageProductDao(private val appDatabase: AppDatabaseHelper) : IPackageProductDao {

    @SuppressLint("Range")
    override suspend fun getAllPackageProduct(): MutableList<PackageProduct> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(Product.TABLE_NAME, null, null, null, null, null, null)
        val list = mutableListOf<PackageProduct>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(
                    PackageProduct(
                        null,
                        cursor.getString(cursor.getColumnIndex(PackageProduct.ID_ITEM_BILL)),
                        cursor.getInt(cursor.getColumnIndex(PackageProduct.QUANTITY))
                    )
                )
                cursor.moveToNext()
            }
        }
        cursor.close()
//        db.close()
        return list
    }

    override suspend fun addPackageProduct(packageProduct: PackageProduct): Long {
        val db = appDatabase.writableDatabase
        return db.insertWithOnConflict(
            PackageProduct.TABLE_NAME,
            null,
            packageProduct.getContentValues(),
            SQLiteDatabase.CONFLICT_REPLACE
        )
//            .also { db.close() }
    }

    override suspend fun getPackageProduct(idPackage: String): MutableList<PackageProduct> {
        val db = appDatabase.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${PackageProduct.TABLE_NAME} bd , ${Product.TABLE_NAME} p  " +
                    "WHERE ${PackageProduct.ID_ITEM_BILL} = $idPackage  " +
                    "and bd.${PackageProduct.ID_ITEM_PRODUCT} = p.${Product.IDPRODUCT}", null
        )
        val list = mutableListOf<PackageProduct>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(PackageProduct(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
//        db.close()
        return list
    }

}