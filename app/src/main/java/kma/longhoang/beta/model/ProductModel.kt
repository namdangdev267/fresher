package kma.longhoang.beta.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor


class ProductModel {
    var id: Int? = null
    var name: String = ""
    var code: String = ""
    var price: Float = 0F
    var style: FilterProduct.Style? = null
    var color: FilterProduct.Color? = null

    constructor()
    constructor(
        name: String,
        code: String,
        price: Float,
        style: FilterProduct.Style,
        color: FilterProduct.Color? = null
    ) {
        this.name = name
        this.code = code
        this.price = price
        this.style = style
        this.color = color
    }

    @SuppressLint("Range")
    constructor(cursor: Cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex("id"))
        this.name = cursor.getString(cursor.getColumnIndex("name"))
        this.code = cursor.getString(cursor.getColumnIndex("code"))
        this.price = cursor.getFloat(cursor.getColumnIndex("price"))
        this.style = FilterProduct.Style.valueOf(cursor.getString(cursor.getColumnIndex("style")))
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
    }

    fun getContentValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put("id", id)
        contentValues.put("name", name)
        contentValues.put("code", code)
        contentValues.put("price", price)
        contentValues.put("style", style?.name)
        contentValues.put("color", color?.name)
        return contentValues
    }
}