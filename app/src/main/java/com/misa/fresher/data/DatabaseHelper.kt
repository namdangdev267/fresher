package com.misa.fresher.data

import android.content.Context
import com.misa.fresher.Queries
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            execSQL(Queries.CREATE_TABLE_PRODUCT)
            execSQL(Queries.CREATE_TABLE_CUSTOMER)
            execSQL(Queries.CREATE_TABLE_BILL)
            execSQL(Queries.CREATE_TABLE_CART)
            execSQL(Queries.INSERT_DEFAULT_PRODUCT)
        }
    }

    override fun onUpgrade(dp: SQLiteDatabase?, p1: Int, p2: Int) {
        dp?.execSQL("DROP TABLE IF EXISTS PRODUCT")
        dp?.execSQL("DROP TABLE IF EXISTS USER")
        dp?.execSQL("DROP TABLE IF EXISTS BIll")
        dp?.execSQL("DROP TABLE IF EXISTS CART")
        onCreate(dp)
    }

    companion object {
        private const val DB_NAME = "shopping.sqlite"
        private const val DB_VERSION = 1
    }
}