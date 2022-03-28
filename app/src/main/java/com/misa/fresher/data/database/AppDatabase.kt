package com.misa.fresher.data.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.misa.fresher.data.Queries
import com.misa.fresher.data.Queries.DATABASE_NAME
import com.misa.fresher.data.Queries.DATABASE_VERSION
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.models.ItemProduct

class AppDatabase private constructor(
    context: Context, dbName: String, cursor: SQLiteDatabase.CursorFactory?, dbVersion: Int
) : SQLiteOpenHelper(context, dbName, cursor, dbVersion) {


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: AppDatabase(
            context,
            DATABASE_NAME,
            null,
            DATABASE_VERSION
        ).also { INSTANCE = it }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
//            execSQL(Queries.CREATE_COLOR_TABLE)
//            execSQL(Queries.CREATE_CATEGORY_TABLE)
//            execSQL(Queries.CREATE_BILL_STATUS_TABLE)
            execSQL(Queries.CREATE_INFO_SHIP_TABLE)
            execSQL(Queries.CREATE_ITEM_PRODUCT_TABLE)
            execSQL(Queries.CREATE_ITEM_BILL_DETAIL_TABLE)
            execSQL(Queries.CREATE_ITEM_BILL_TABLE)

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.run {
//            execSQL(Queries.DROP_COLOR_TABLE)
//            execSQL(Queries.DROP_CATEGORY_TABLE)
//            execSQL(Queries.DROP_BILL_STATUS_TABLE)
            execSQL(Queries.DROP_INFO_SHIP_TABLE)
            execSQL(Queries.DROP_ITEM_PRODUCT_TABLE)
            execSQL(Queries.DROP_ITEM_BILL_DETAIL_TABLE)
            execSQL(Queries.DROP_ITEM_BILL_TABLE)
            onCreate(this)
        }
    }
}

