package com.misa.fresher.data.source.local.dao

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.model.IDatabaseModel
import com.misa.fresher.data.source.local.database.AppDbHelper
import com.misa.fresher.utils.handleException

/**
 * - interface's purpose: base interface for dao interface
 *
 * @author HTLong
 * @edit_at 24/03/2022
 */
abstract class BaseDao<T: IDatabaseModel>(protected val dbHelper: AppDbHelper) {
    protected abstract val tableName: String
    protected abstract fun fromCursor(cursor: Cursor): T?

    abstract fun update(row: T): Long
    abstract fun delete(row: T): Long

    fun insert(row: T): Long {
        return try {
            val db: SQLiteDatabase = dbHelper.writableDatabase
            val contentValues = row.getContentValues()
            db.insertWithOnConflict(tableName, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE).also {
                db.close()
            }
        } catch (e: Exception) {
            handleException(e)
            -1
        }

    }

    fun getByCol(colName: String? = null, colValue: String? = null): ArrayList<T> {
        var sql = "SELECT * FROM $tableName"
        if(colName != null) sql += " WHERE $colName = $colValue"

        return getBySql(sql)
    }

    protected fun getBySql(sql: String): ArrayList<T> {
        val db: SQLiteDatabase = dbHelper.readableDatabase

        val cursor = db.rawQuery(sql, null)
        val results = arrayListOf<T>()

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                fromCursor(cursor)?.also { results.add(it) }
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return results
    }
}