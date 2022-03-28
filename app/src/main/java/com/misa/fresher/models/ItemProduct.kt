package com.misa.fresher.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import com.misa.fresher.R
import com.misa.fresher.getNumString
import com.misa.fresher.models.enums.Category
import com.misa.fresher.models.enums.Color
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import java.util.*


@Parcelize
class ItemProduct() : Parcelable {

    var id = ""
    var name: String = ""
    var price: Float = 0f
    var code: String = ""
    var color: String = ""
    var category: String = ""
    var availableQuantity: Int = 0
    var dateArrival: String = ""


    val priceForView: String
        get() = price.toString()

    val idForView: String
        get() = id.getNumString()

//    val imageResourceForList:Int
//        get() = R.drawable.ic_shopping_bag

    constructor(
        name: String,
        price: Float,
        code: String,
        color: String,
        category: String,
        availableQuantity: Int,
        dateArrival: String
    ) : this() {
        this.id = UUID.randomUUID().toString()
        this.name = name
        this.price = price
        this.code = code
        this.color = color
        this.category = category
        this.availableQuantity = availableQuantity
        this.dateArrival = dateArrival
    }

    @SuppressLint("Range")
    constructor(cursor: Cursor) : this() {
        this.id = cursor.getString(cursor.getColumnIndex(ItemProduct.ID))
        this.name = cursor.getString(cursor.getColumnIndex(ItemProduct.NAME))
        this.price = cursor.getFloat(cursor.getColumnIndex(ItemProduct.PRICE))
        this.code = cursor.getString(cursor.getColumnIndex(ItemProduct.CODE))
        this.color = cursor.getString(cursor.getColumnIndex(ItemProduct.COLOR))
        this.category = cursor.getString(cursor.getColumnIndex(ItemProduct.CATEGORY))
        this.availableQuantity = cursor.getInt(cursor.getColumnIndex(ItemProduct.AVAILABLE_QUANTITY))
        this.dateArrival = cursor.getString(cursor.getColumnIndex(ItemProduct.DATE_ARRIVAL))
    }




    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID, id)
            put(NAME, name)
            put(PRICE, price)
            put(CODE, code)
            put(COLOR, color)
            put(CATEGORY, category)
            put(AVAILABLE_QUANTITY, availableQuantity)
            put(DATE_ARRIVAL, dateArrival)
        }
    }

    companion object {
        const val TABLE_NAME: String = "ITEM_PRODUCT_TABLE"
        const val ID: String = "id"
        const val CODE: String = "code"
        const val NAME: String = "name"
        const val PRICE: String = "price"
        const val COLOR: String = "color"
        const val CATEGORY: String = "category"
        const val AVAILABLE_QUANTITY: String = "availableQuantity"
        const val DATE_ARRIVAL: String = "dateArrival"
    }
}