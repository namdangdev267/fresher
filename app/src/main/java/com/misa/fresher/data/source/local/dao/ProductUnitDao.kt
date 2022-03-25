package com.misa.fresher.data.source.local.dao

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.model.product.ModelUnit
import com.misa.fresher.data.model.product.ProductUnit
import com.misa.fresher.data.source.local.database.AppDbHelper

class ProductUnitDao private constructor(dbHelper: AppDbHelper) : BaseDao<ProductUnit>(dbHelper) {
    override val tableName: String = ProductUnit.TABLE_NAME
    override fun fromCursor(cursor: Cursor): ProductUnit? = ProductUnit.fromCursor(cursor)


    override fun update(row: ProductUnit): Long {
        TODO("Not yet implemented")
    }

    override fun delete(row: ProductUnit): Long {
        TODO("Not yet implemented")
    }

    fun getByModelId(modelId: Int): ArrayList<ProductUnit> {
        val sql =
            "SELECT * FROM $tableName WHERE ${ProductUnit.ID} in (select ${ModelUnit.UNIT_ID} from ${ModelUnit.TABLE_NAME} where ${ModelUnit.MODEL_ID} = $modelId)"

        return getBySql(sql)
    }

    companion object {
        private var instance: ProductUnitDao? = null
        fun getInstance(dbHelper: AppDbHelper) = instance ?: ProductUnitDao(dbHelper).also { instance = it }
    }
}