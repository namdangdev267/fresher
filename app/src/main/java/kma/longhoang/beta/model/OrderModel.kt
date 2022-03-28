package kma.longhoang.beta.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor

class OrderModel {
    var id: Int? = null
    var name: String? = null
    var code: String? = null
    var color: FilterProduct.Color? = null
    var price: Float = 0F
    var amount: Int = 0
    var billId: Int? = null

    constructor()
    constructor(
        name: String? = null,
        code: String? = null,
        color: FilterProduct.Color? = null,
        price: Float?,
        amount: Int
    ) {
        this.name = name
        this.code = code
        this.color = color
        if (price != null) {
            this.price = price
        }
        this.amount = amount
    }

    @SuppressLint("Range")
    constructor(cursor: Cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex("id"))
        this.name = cursor.getString(cursor.getColumnIndex("name"))
        this.code = cursor.getString(cursor.getColumnIndex("code"))
        val colorProduct = cursor.getString(cursor.getColumnIndex("color"))
        if (colorProduct == null) {
            this.color = null
        } else {
            for (color in FilterProduct.Color.values()) {
                if (colorProduct == color.name) {
                    this.color = color
                }
            }
        }
        this.price = cursor.getFloat(cursor.getColumnIndex("price"))
        this.amount = cursor.getInt(cursor.getColumnIndex("amount"))
        this.billId = cursor.getInt(cursor.getColumnIndex("billId"))
    }

}