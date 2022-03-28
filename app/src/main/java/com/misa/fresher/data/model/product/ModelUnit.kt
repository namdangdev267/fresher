package com.misa.fresher.data.model.product

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.base.IDatabaseModel
import com.misa.fresher.utils.handleException
import kotlinx.parcelize.Parcelize


@Parcelize
data class ModelUnit(
    val id: Int = -1, val modelId: Int, val unitId: Int
) : Parcelable, IDatabaseModel {

    override fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(MODEL_ID, modelId)
            put(UNIT_ID, unitId)
        }
    }

    companion object {
        const val TABLE_NAME: String = "MODEL_UNIT"
        const val ID: String = "id"
        const val MODEL_ID: String = "model_id"
        const val UNIT_ID: String = "unit_id"

        fun fromCursor(cursor: Cursor): ModelUnit? {
            return try {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                val modelId = cursor.getInt(cursor.getColumnIndexOrThrow(MODEL_ID))
                val unitId = cursor.getInt(cursor.getColumnIndexOrThrow(UNIT_ID))

                ModelUnit(id, modelId, unitId)

            } catch (e: Exception) {
                handleException(e)
                null
            }
        }
    }
}