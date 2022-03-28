package com.misa.fresher.data.model.product

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.base.IDatabaseModel
import com.misa.fresher.utils.handleException
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUnit(
    val id: Int = -1, val name: String = "", val value: Int = 1
) : Parcelable, IDatabaseModel {

    override fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(NAME, name)
            put(VALUE, value)
        }
    }

    companion object {
        const val TABLE_NAME: String = "PRODUCT_UNIT"
        const val ID: String = "id"
        const val NAME: String = "name"
        const val VALUE: String = "value"

        fun fromCursor(cursor: Cursor): ProductUnit? {
            return try {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                val code = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                val value = cursor.getInt(cursor.getColumnIndexOrThrow(VALUE))

                ProductUnit(id, code, value)

            } catch (e: Exception) {
                handleException(e)
                null
            }
        }
    }
}