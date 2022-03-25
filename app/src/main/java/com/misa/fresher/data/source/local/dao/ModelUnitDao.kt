package com.misa.fresher.data.source.local.dao

import android.database.Cursor
import com.misa.fresher.data.model.product.ModelUnit
import com.misa.fresher.data.source.local.database.AppDbHelper

class ModelUnitDao private constructor(dbHelper: AppDbHelper) : BaseDao<ModelUnit>(dbHelper){
    override val tableName: String = ModelUnit.TABLE_NAME
    override fun fromCursor(cursor: Cursor): ModelUnit? = ModelUnit.fromCursor(cursor)

    override fun update(row: ModelUnit): Long {
        TODO("Not yet implemented")
    }

    override fun delete(row: ModelUnit): Long {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: ModelUnitDao? = null
        fun getInstance(dbHelper: AppDbHelper) = instance ?: ModelUnitDao(dbHelper).also { instance = it }
    }
}