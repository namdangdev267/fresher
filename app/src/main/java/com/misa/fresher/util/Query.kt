package com.misa.fresher.util

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * Thực hiện câu query theo kiểu builder
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
class Query(
    private val db: SQLiteDatabase,
    private val table: String
) {
    private var columns: Array<String>? = null
    private var selection: String? = null
    private var selectionArgs: Array<String>? = null
    private var groupBy: String? = null
    private var having: String? = null
    private var orderBy: String? = null
    private var limit: String? = null

    fun setColumns(columns: Array<String>?): Query {
        this.columns = columns
        return this
    }

    fun setSelection(selection: String?): Query {
        this.selection = selection
        return this
    }

    fun setSelectionArgs(selectionArgs: Array<String>?): Query {
        this.selectionArgs = selectionArgs
        return this
    }
    fun setGroupBy(groupBy: String?): Query {
        this.groupBy = groupBy
        return this
    }

    fun setHaving(having: String?): Query {
        this.having = having
        return this
    }

    fun setOrderBy(orderBy: String?): Query {
        this.orderBy = orderBy
        return this
    }

    fun setLimit(limit: String?): Query {
        this.limit = limit
        return this
    }

    fun call(): Cursor {
        return db.query(
            table,
            columns,
            selection,
            selectionArgs,
            groupBy,
            having,
            orderBy,
            limit
        )
    }
}