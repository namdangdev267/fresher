package com.misa.fresher.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "MisaFresher_db"
        private const val DB_TABLE = "ProductSale"
        private const val DB_VERSION = 1

        private const val COLUMN_NAME = "name"
        private const val COLUMN_CODE = "id"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_QUANTITY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createProductTable = "CREATE TABLE " + DB_TABLE + "(" +
                "name Text" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "price INTEGER" +
                "quantity INTEGER"
        db?.execSQL(createProductTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS" + DB_TABLE)
            onCreate(db)
        }
    }


}