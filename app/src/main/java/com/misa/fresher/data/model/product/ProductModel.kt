package com.misa.fresher.data.model.product

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.base.IDatabaseModel
import com.misa.fresher.utils.handleException
import kotlinx.parcelize.Parcelize
import kotlin.collections.ArrayList

@Parcelize
data class ProductModel(
    var id: Int = -1,
    var code: String = "",
    var name: String = "",
    var category: String = "",
    var date: Long = 0,
    var image: Int = 0,

    var items: ArrayList<ProductItem> = arrayListOf(),
    var units: ArrayList<ProductUnit> = arrayListOf(),
    var unit: ProductUnit = ProductUnit(-1, "", 1),
    var amount: Int = 0
) : Parcelable, IDatabaseModel {

    val quantity get() = items.sumOf { it.quantity }
    val price get() = items[0].price * unit.value * amount

    override fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(CODE, code)
            put(NAME, name)
            put(CATEGORY, category)
            put(DATE, date)
            put(IMAGE, image)
        }
    }

    companion object {
        const val TABLE_NAME: String = "PRODUCT_MODEL"
        const val ID: String = "id"
        const val CODE: String = "code"
        const val NAME: String = "name"
        const val CATEGORY: String = "category"
        const val DATE: String = "date"
        const val IMAGE: String = "image"

        fun fromCursor(cursor: Cursor): ProductModel? {
            return try {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                val code = cursor.getString(cursor.getColumnIndexOrThrow(CODE))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                val category = cursor.getString(cursor.getColumnIndexOrThrow(CATEGORY))
                val date = cursor.getLong(cursor.getColumnIndexOrThrow(DATE))
                val image = cursor.getInt(cursor.getColumnIndexOrThrow(IMAGE))

                ProductModel(id, code, name, category, date, image)

            } catch (e: Exception) {
                handleException(e)
                null
            }
        }
    }
}