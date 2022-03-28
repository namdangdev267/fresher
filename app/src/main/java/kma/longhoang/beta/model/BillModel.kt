package kma.longhoang.beta.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import kma.longhoang.beta.BillState
import java.util.*

class BillModel {
    var id: Int? = null
    var customerId: Int? = null
    var customer: CustomerModel? = null
    var listOrder: MutableList<OrderModel>? = null
    var date: String? = null
    var state: BillState? = null

    constructor(
        id: Int,
        listOrder: MutableList<OrderModel>,
        customerModel: CustomerModel? = null,
        date: String,
        state: BillState
    ) {
        this.id = id
        this.listOrder = listOrder
        this.customer = customerModel
        this.date = date
        this.state = state
    }

    constructor(id: Int?, customerId: Int? = null, date: String, state: BillState) {
        this.id = id
        this.customerId = customerId
        this.date = date
        this.state = state
    }

    @SuppressLint("Range")
    constructor(cursor: Cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex("id"))
        this.customerId = cursor.getInt(cursor.getColumnIndex("customerId"))
        this.date = cursor.getString(cursor.getColumnIndex("date"))
        val stateBill = cursor.getString(cursor.getColumnIndex("state"))
        if (stateBill == null) {
            this.state = null
        } else {
            for (state in BillState.values()) {
                if (stateBill == state.name) {
                    this.state = state
                }
            }
        }
    }

    fun getContentValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put("id", id)
        contentValues.put("customerId", customerId)
        contentValues.put("date", date)
        contentValues.put("state", state?.name)
        return contentValues
    }

}