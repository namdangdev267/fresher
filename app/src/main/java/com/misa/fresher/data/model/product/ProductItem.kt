package com.misa.fresher.data.model.product

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.base.IDatabaseModel
import com.misa.fresher.utils.handleException
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    val id: Int = -1,
    val modelId: Int,
    val code: String,
    val name: String,
    val size: String,
    val color: String,
    var price: Double,
    var quantity: Int
): Parcelable, IDatabaseModel {

    override fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(MODEL_ID, modelId)
            put(CODE, code)
            put(NAME, name)
            put(SIZE, size)
            put(COLOR, color)
            put(PRICE, price)
            put(QUANTITY, quantity)
        }
    }

    companion object {
        const val TABLE_NAME: String = "PRODUCT_ITEM"
        const val ID: String = "id"
        const val MODEL_ID: String = "model_id"
        const val CODE: String = "code"
        const val NAME: String = "name"
        const val SIZE: String = "size"
        const val COLOR: String = "color"
        const val PRICE: String = "price"
        const val QUANTITY: String = "quantity"

        fun fromCursor(cursor: Cursor): ProductItem? {
            return try {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                val modelId = cursor.getInt(cursor.getColumnIndexOrThrow(MODEL_ID))
                val code = cursor.getString(cursor.getColumnIndexOrThrow(CODE))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                val size = cursor.getString(cursor.getColumnIndexOrThrow(SIZE))
                val color = cursor.getString(cursor.getColumnIndexOrThrow(COLOR))
                val price = cursor.getDouble(cursor.getColumnIndexOrThrow(PRICE))
                val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(QUANTITY))

                ProductItem(id, modelId, code, name, size, color, price, quantity)

            } catch (e: Exception) {
                handleException(e)
                null
            }
        }
    }
}