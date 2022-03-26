package com.misa.fresher.data.source

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.misa.fresher.data.Queries.CREATE_ITEM_BILL_TABLE
import com.misa.fresher.data.Queries.CREATE_PACKAGE_PRODUCT_TABLE
import com.misa.fresher.data.Queries.CREATE_PRODUCT_TABLE
import com.misa.fresher.data.Queries.DATABASE_NAME
import com.misa.fresher.data.Queries.DATABASE_VERSION
import com.misa.fresher.data.Queries.DROP_ITEM_BILL_TABLE
import com.misa.fresher.data.Queries.DROP_PACKAGE_PRODUCT_TABLE
import com.misa.fresher.data.Queries.DROP_PRODUCT_TABLE

class AppDatabaseHelper private constructor(
    context: Context,
    databaseName: String,
    cursor: SQLiteDatabase.CursorFactory?,
    databseVersion: Int
) : SQLiteOpenHelper(context, databaseName, cursor, databseVersion) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            execSQL(CREATE_PRODUCT_TABLE)
            execSQL(CREATE_PACKAGE_PRODUCT_TABLE)
            execSQL(CREATE_ITEM_BILL_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldDataVersion: Int, newDataVersion: Int) {
        db?.run {
            execSQL(DROP_PRODUCT_TABLE)
            execSQL(DROP_PACKAGE_PRODUCT_TABLE)
            execSQL(DROP_ITEM_BILL_TABLE)
            onCreate(this)
        }
    }

    companion object {
        private var instance: AppDatabaseHelper? = null
        fun getInstance(context: Context): AppDatabaseHelper = instance ?: AppDatabaseHelper(
            context,
            DATABASE_NAME,
            null,
            DATABASE_VERSION
        ).also { instance = it }
    }
}