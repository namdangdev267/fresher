package com.misa.fresher.data.source.local.dao

import android.database.Cursor
import com.misa.fresher.data.model.product.ProductUnit

interface IProductDao {
    interface IBase<T> {
        val tableName: String
        fun fromCursor(cursor: Cursor): T?
        fun insert(row: T): Long
        fun update(row: T): Long
        fun delete(row: T): Long
        fun getByCol(colName: String? = null, colValue: String? = null): ArrayList<T>
    }

    interface IItemBill<T> : IBase<T> {

    }

    interface IModelUnit<T> : IBase<T> {

    }

    interface IBill<T> : IBase<T> {

    }

    interface IItem<T> : IBase<T> {

    }

    interface IModel<T> : IBase<T> {

    }

    interface IUnit<T> : IBase<T> {
        fun getByModelId(modelId: Int): ArrayList<ProductUnit>
    }
}