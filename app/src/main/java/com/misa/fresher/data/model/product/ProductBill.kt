package com.misa.fresher.data.model.product

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.base.IDatabaseModel
import com.misa.fresher.utils.handleException
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductBill(
    val id: Int = -1,
    var date: Long,
    var customer: String,
    
    var productModels: ArrayList<ProductModel> = arrayListOf()
) : Parcelable, IDatabaseModel {
    val price: Double
        get() = productModels.sumOf { it.price }

    override fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(DATE, date)
            put(CUSTOMER, customer)
        }
    }
    
    companion object {
        const val TABLE_NAME: String = "PRODUCT_BILL"
        const val ID: String = "id"
        const val DATE: String = "date"
        const val CUSTOMER: String = "customer"

        fun fromCursor(cursor: Cursor): ProductBill? {
            return try {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                val date = cursor.getLong(cursor.getColumnIndexOrThrow(DATE))
                val customer = cursor.getString(cursor.getColumnIndexOrThrow(CUSTOMER))

                ProductBill(id, date, customer)
            } catch (e: Exception) {
                handleException(e)
                null
            }
        }
    }
}
