package com.misa.fresher.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.misa.fresher.utils.Queries.CREATE_BILL_TABLE
import com.misa.fresher.utils.Queries.CREATE_CUSTOMER_TABLE
import com.misa.fresher.utils.Queries.CREATE_PRODUCT_TABLE
import com.misa.fresher.utils.Queries.CREATE_SELECTED_PRODUCT_TABLE
import com.misa.fresher.utils.Queries.DATABASE_NAME
import com.misa.fresher.utils.Queries.DATABASE_VERSION
import com.misa.fresher.utils.Queries.DROP_BILL_TABLE
import com.misa.fresher.utils.Queries.DROP_CUSTOMER_TABLE
import com.misa.fresher.utils.Queries.DROP_PRODUCT_TABLE
import com.misa.fresher.utils.Queries.DROP_SELECTED_PRODUCT_TABLE
import com.misa.fresher.utils.Queries.INSERT_DEFAULT_BILL
import com.misa.fresher.utils.Queries.INSERT_DEFAULT_PRODUCT
import com.misa.fresher.utils.Queries.INSERT_DEFAULT_SELECTEDPRODUCT
import com.misa.fresher.utils.Queries.INSERT_DEFAUL_CUSTOMER

class AppDbHelper private constructor(
    context: Context, dbName: String, cursor: SQLiteDatabase.CursorFactory?, dbVersion: Int
) : SQLiteOpenHelper(context, dbName, cursor, dbVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            execSQL(CREATE_PRODUCT_TABLE)
            execSQL(CREATE_CUSTOMER_TABLE)
            execSQL(CREATE_BILL_TABLE)
            execSQL(CREATE_SELECTED_PRODUCT_TABLE)
            execSQL(INSERT_DEFAULT_PRODUCT)
            execSQL(INSERT_DEFAUL_CUSTOMER)
            execSQL(INSERT_DEFAULT_BILL)
            execSQL(INSERT_DEFAULT_SELECTEDPRODUCT)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.run {
            execSQL(DROP_PRODUCT_TABLE)
            execSQL(DROP_CUSTOMER_TABLE)
            execSQL(DROP_BILL_TABLE)
            execSQL(DROP_SELECTED_PRODUCT_TABLE)
        }.also { onCreate(db) }
    }
    companion object {
        private var instance: AppDbHelper? = null
        fun getInstance(context: Context): AppDbHelper = instance ?: AppDbHelper(
            context,
            DATABASE_NAME,
            null,
            DATABASE_VERSION
        ).also { instance = it }
    }
}