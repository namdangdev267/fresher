package com.misa.fresher.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.misa.fresher.developer.FakeDbData
import com.misa.fresher.utils.Queries.CREATE_ITEM_BILL_TABLE
import com.misa.fresher.utils.Queries.CREATE_MODEL_UNIT_TABLE
import com.misa.fresher.utils.Queries.CREATE_PRODUCT_BILL_TABLE
import com.misa.fresher.utils.Queries.CREATE_PRODUCT_ITEM_TABLE
import com.misa.fresher.utils.Queries.CREATE_PRODUCT_MODEL_TABLE
import com.misa.fresher.utils.Queries.CREATE_PRODUCT_UNIT_TABLE
import com.misa.fresher.utils.Queries.DATABASE_NAME
import com.misa.fresher.utils.Queries.DATABASE_VERSION
import com.misa.fresher.utils.Queries.DROP_ITEM_BILL_TABLE
import com.misa.fresher.utils.Queries.DROP_MODEL_UNIT_TABLE
import com.misa.fresher.utils.Queries.DROP_PRODUCT_BILL_TABLE
import com.misa.fresher.utils.Queries.DROP_PRODUCT_ITEM_TABLE
import com.misa.fresher.utils.Queries.DROP_PRODUCT_MODEL_TABLE
import com.misa.fresher.utils.Queries.DROP_PRODUCT_UNIT_TABLE

/**
 * - Class's purpose:
 *
 * @author HTLong
 * @edit_at 23/03/2022
 */


class AppDbHelper private constructor(
    context: Context, dbName: String, cursor: SQLiteDatabase.CursorFactory?, dbVersion: Int
) : SQLiteOpenHelper(context, dbName, cursor, dbVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            execSQL(CREATE_PRODUCT_UNIT_TABLE)
            execSQL(CREATE_PRODUCT_ITEM_TABLE)
            execSQL(CREATE_PRODUCT_MODEL_TABLE)
            execSQL(CREATE_PRODUCT_BILL_TABLE)
            execSQL(CREATE_MODEL_UNIT_TABLE)
            execSQL(CREATE_ITEM_BILL_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.run {
            execSQL(DROP_PRODUCT_UNIT_TABLE)
            execSQL(DROP_PRODUCT_ITEM_TABLE)
            execSQL(DROP_PRODUCT_MODEL_TABLE)
            execSQL(DROP_PRODUCT_BILL_TABLE)
            execSQL(DROP_MODEL_UNIT_TABLE)
            execSQL(DROP_ITEM_BILL_TABLE)
            onCreate(this)
        }
    }

    companion object {
        private var instance: AppDbHelper? = null
        fun getInstance(context: Context) =
            instance ?: AppDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION).also { instance = it }
    }
}