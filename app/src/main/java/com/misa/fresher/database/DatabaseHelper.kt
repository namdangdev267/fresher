package com.misa.fresher.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context) : SQLiteOpenHelper(context,"course.db",null,1){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.run {
            execSQL(Queries.CREATE_PRODUCT_TABLE)
            execSQL(Queries.CREATE_CUSTOMER_TABLE)
            execSQL(Queries.CREATE_BILL_TABLE)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS PRODUCT")
        p0?.execSQL("DROP TABLE IF EXISTS CUSTOMER")
        p0?.execSQL("DROP TABLE IF EXISTS BILL")
        onCreate(p0)
    }
}