package com.misa.fresher.data.model.product

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.data.model.IDatabaseModel
import com.misa.fresher.utils.handleException
import kotlinx.parcelize.Parcelize
import java.time.temporal.TemporalAmount


@Parcelize
data class ItemBill(
    val id: Int = -1, val itemId: Int, val billId: Int, val amount: Int
) : Parcelable, IDatabaseModel {

    override fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ITEM_ID, itemId)
            put(BILL_ID, billId)
            put(AMOUNT, amount)
        }
    }

    companion object {
        const val TABLE_NAME: String = "ITEM_BILL"
        const val ID: String = "id"
        const val ITEM_ID: String = "item_id"
        const val BILL_ID: String = "bill_id"
        const val AMOUNT: String = "amount"

        fun fromCursor(cursor: Cursor): ItemBill? {
            return try {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                val itemId = cursor.getInt(cursor.getColumnIndexOrThrow(ITEM_ID))
                val billId = cursor.getInt(cursor.getColumnIndexOrThrow(BILL_ID))
                val amount = cursor.getInt(cursor.getColumnIndexOrThrow(AMOUNT))

                ItemBill(id, itemId, billId, amount)

            } catch (e: Exception) {
                handleException(e)
                null
            }
        }
    }
}