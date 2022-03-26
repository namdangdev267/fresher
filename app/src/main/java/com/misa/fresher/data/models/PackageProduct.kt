package com.misa.fresher.data.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class PackageProduct() : Parcelable {
    var id: String = ""
    var namePackage: String = ""
    var codePackage: String = ""
    var product: Product? = null
    var idBill: String = ""
    var countPackage: Int = 0

    constructor(product: Product?, idBill: String, countPackage: Int) : this() {
        this.namePackage = product?.nameProduct + "(" + product?.color?.lowercase() + ")"
        this.codePackage = product?.codeProduct + "(" + product?.color?.lowercase() + ")"
        this.id = UUID.randomUUID().toString()
        this.product = product
        this.idBill = idBill
        this.countPackage = countPackage
    }

    @SuppressLint("Range")
    constructor(cursor: Cursor) : this() {
        PackageProduct(
            Product(
                cursor.getInt(cursor.getColumnIndex(Product.IMGPRODUCT)),
                cursor.getString(cursor.getColumnIndex(Product.NAMEPRODUCT)),
                cursor.getString(cursor.getColumnIndex(Product.CODEPRODUCT)),
                cursor.getString(cursor.getColumnIndex(Product.COLOR)),
                cursor.getString(cursor.getColumnIndex(Product.CATEGORY)),
                cursor.getInt(cursor.getColumnIndex(Product.PRICEPRODUCT)),
                cursor.getInt(cursor.getColumnIndex(Product.CODEPRODUCT)),
                cursor.getString(cursor.getColumnIndex(Product.DATEARRIVAL))
            ), cursor.getString(cursor.getColumnIndex(ID_ITEM_BILL)),
            cursor.getInt(cursor.getColumnIndex(QUANTITY))
        )
    }

    fun getPrice(): Int = countPackage * product!!.priceProduct

    fun updateCount(quantity: Int) {
        this.countPackage += quantity
    }

    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID, id)
            put(NAME, namePackage)
            put(CODE, codePackage)
            put(ID_ITEM_PRODUCT, product!!.id)
            put(ID_ITEM_BILL, idBill)
            put(QUANTITY, countPackage)
        }
    }

    companion object {
        const val TABLE_NAME = "PackageProduct"
        const val ID: String = "id"
        const val NAME: String = "namePackage"
        const val CODE: String = "codePackage"
        const val ID_ITEM_PRODUCT: String = "product"
        const val ID_ITEM_BILL: String = "idBill"
        const val QUANTITY: String = "countPackage"
    }
}