package com.misa.fresher.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.misa.fresher.common.Constant
import com.misa.fresher.common.FakeData
import com.misa.fresher.data.entity.*

/**
 * Helper chứa hàm get database, ngoài ra còn sử dụng để tạo, cập nhật database
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 2
 * @updated 3/24/2022: Tạo class
 * @updated 3/28/2022: Thêm dữ liệu giả ngay lúc khởi tạo csdl
 */
class AppDatabase private constructor(context: Context)
    : SQLiteOpenHelper(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION) {

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

            setDefaultCategory(this)
            setDefaultCustomer(this)
            setDefaultColor(this)
            setDefaultSize(this)
            setDefaultUnit(this)
            setDefaultProduct(this)
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

    private fun setDefaultCategory(db: SQLiteDatabase) {
        db.beginTransaction()

        var isSuccess = true
        for (item in FakeData.category) {
            val values = item.getContentValues()
            if (db.insertWithOnConflict(
                    Category.TABLE_NAME,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE) <= 0) {
                isSuccess = false
                break
            }
        }
        if (isSuccess) {
            db.setTransactionSuccessful()
        }
        db.endTransaction()
    }

    private fun setDefaultCustomer(db: SQLiteDatabase) {
        db.beginTransaction()

        var isSuccess = true
        for (item in FakeData.customers) {
            val values = item.getContentValues()
            if (db.insertWithOnConflict(
                    Customer.TABLE_NAME,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE) <= 0) {
                isSuccess = false
                break
            }
        }
        if (isSuccess) {
            db.setTransactionSuccessful()
        }
        db.endTransaction()
    }

    private fun setDefaultColor(db: SQLiteDatabase) {
        db.beginTransaction()

        var isSuccess = true
        for (item in FakeData.colors) {
            val values = item.getContentValues()
            if (db.insertWithOnConflict(
                    ProductColor.TABLE_NAME,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE) <= 0) {
                isSuccess = false
                break
            }
        }
        if (isSuccess) {
            db.setTransactionSuccessful()
        }
        db.endTransaction()
    }

    private fun setDefaultSize(db: SQLiteDatabase) {
        db.beginTransaction()

        var isSuccess = true
        for (item in FakeData.sizes) {
            val values = item.getContentValues()
            if (db.insertWithOnConflict(
                    ProductSize.TABLE_NAME,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE) <= 0) {
                isSuccess = false
                break
            }
        }
        if (isSuccess) {
            db.setTransactionSuccessful()
        }
        db.endTransaction()
    }

    private fun setDefaultUnit(db: SQLiteDatabase) {
        db.beginTransaction()

        var isSuccess = true
        for (item in FakeData.units) {
            val values = item.getContentValues()
            if (db.insertWithOnConflict(
                    ProductUnit.TABLE_NAME,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE) <= 0) {
                isSuccess = false
                break
            }
        }
        if (isSuccess) {
            db.setTransactionSuccessful()
        }
        db.endTransaction()
    }

    private fun setDefaultProduct(db: SQLiteDatabase) {
        db.beginTransaction()

        var isSuccess = true
        for (item in FakeData.products) {
            val values = item.getContentValues()
            if (db.insertWithOnConflict(
                    Product.TABLE_NAME,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE) > 0) {
                for (ite in item.items) {
                    val itemValues = ite.getContentValues()
                    itemValues.put(ProductItem.PRODUCT_ID, item.id)
                    if (db.insertWithOnConflict(
                            ProductItem.TABLE_NAME,
                            null,
                            itemValues,
                            SQLiteDatabase.CONFLICT_REPLACE) <= 0) {
                        isSuccess = false
                        break
                    }
                }
            } else {
                isSuccess = false
                break

            }
        }
        if (isSuccess) {
            db.setTransactionSuccessful()
        }
        db.endTransaction()
    }

    companion object {
        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            appDatabase ?: AppDatabase(context).also { appDatabase = it }
    }
}