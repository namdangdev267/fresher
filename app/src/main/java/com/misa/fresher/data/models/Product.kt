package com.misa.fresher.data.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Product() : Parcelable {
    var id: String = ""
    var imgProduct: Int = 0
    var nameProduct: String = ""
    var codeProduct: String = ""
    var color: String = ""
    var category: String = ""
    var priceProduct: Int = 0
    var quantity: Int = 0
    var dateArrival: String = ""

    constructor(
        imgProduct: Int,
        nameProduct: String,
        codeProduct: String,
        color: String,
        category: String,
        priceProduct: Int,
        quantity: Int,
        dateArrival: String
    ) : this() {
        this.id = UUID.randomUUID().toString()
        this.imgProduct = imgProduct
        this.nameProduct = nameProduct
        this.codeProduct = codeProduct
        this.color = color
        this.category = category
        this.priceProduct = priceProduct
        this.quantity = quantity
        this.dateArrival = dateArrival
    }

    @SuppressLint("Range")
    constructor(cursor: Cursor) : this() {
        try {
            this.id = cursor.getString(cursor.getColumnIndex(IDPRODUCT))
            this.imgProduct = cursor.getInt(cursor.getColumnIndex(IMGPRODUCT))
            this.nameProduct = cursor.getString(cursor.getColumnIndex(NAMEPRODUCT))
            this.codeProduct = cursor.getString(cursor.getColumnIndex(CODEPRODUCT))
            this.color = cursor.getString(cursor.getColumnIndex(COLOR))
            this.category = cursor.getString(cursor.getColumnIndex(CATEGORY))
            this.priceProduct = cursor.getInt(cursor.getColumnIndex(PRICEPRODUCT))
            this.quantity = cursor.getInt(cursor.getColumnIndex(CODEPRODUCT))
            this.dateArrival = cursor.getString(cursor.getColumnIndex(CODEPRODUCT))
        } catch (e: Exception) {

        }
    }

    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(IDPRODUCT, id)
            put(IMGPRODUCT, imgProduct)
            put(NAMEPRODUCT, nameProduct)
            put(CODEPRODUCT, codeProduct)
            put(COLOR, color)
            put(CATEGORY, category)
            put(PRICEPRODUCT, priceProduct)
            put(QUANTITY, quantity)
            put(DATEARRIVAL, dateArrival)
        }
    }

    companion object {
        const val TABLE_NAME = "Product"
        const val IDPRODUCT = "id"
        const val IMGPRODUCT = "imgProduct"
        const val NAMEPRODUCT = "nameProduct"
        const val CODEPRODUCT = "codeProduct"
        const val COLOR = "color"
        const val CATEGORY = "category"
        const val PRICEPRODUCT = "priceProduct"
        const val QUANTITY = "quantity"
        const val DATEARRIVAL = "dateArrival"
    }
}
