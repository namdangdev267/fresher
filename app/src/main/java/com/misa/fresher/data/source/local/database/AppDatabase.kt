package com.misa.fresher.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.misa.fresher.common.Constant

/**
 * Helper chứa hàm get database, ngoài ra còn sử dụng để tạo, cập nhật database
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
class AppDatabase private constructor(context: Context) :
    SQLiteOpenHelper(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            execSQL(Constant.CREATE_CATEGORY_TABLE)
            execSQL(Constant.CREATE_CUSTOMER_TABLE)
            execSQL(Constant.CREATE_PRODUCT_COLOR_TABLE)
            execSQL(Constant.CREATE_PRODUCT_SIZE_TABLE)
            execSQL(Constant.CREATE_PRODUCT_UNIT_TABLE)
            execSQL(Constant.CREATE_PRODUCT_ITEM_TABLE)
            execSQL(Constant.CREATE_PRODUCT_TABLE)
            execSQL(Constant.CREATE_PRODUCT_ITEM_BILL_TABLE)
            execSQL(Constant.CREATE_BILL_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.run {
            execSQL(Constant.DROP_BILL_TABLE)
            execSQL(Constant.DROP_PRODUCT_ITEM_BILL_TABLE)
            execSQL(Constant.DROP_PRODUCT_TABLE)
            execSQL(Constant.DROP_PRODUCT_ITEM_TABLE)
            execSQL(Constant.DROP_PRODUCT_UNIT_TABLE)
            execSQL(Constant.DROP_PRODUCT_SIZE_TABLE)
            execSQL(Constant.DROP_PRODUCT_COLOR_TABLE)
            execSQL(Constant.DROP_CUSTOMER_TABLE)
            execSQL(Constant.DROP_CATEGORY_TABLE)
        }

        onCreate(db)
    }

    companion object {
        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            appDatabase ?: AppDatabase(context).also { appDatabase = it }
    }
}