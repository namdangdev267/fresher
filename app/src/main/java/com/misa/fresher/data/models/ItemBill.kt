package com.misa.fresher.data.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class ItemBill() : Parcelable {
    var id: String = ""
    var listItemBillDetail: MutableList<PackageProduct>? = mutableListOf()
    var inforShip: InforShip? = null
    var status: String = ""
    var createDay: String = ""
    var billPrice: Float = 0f


    @SuppressLint("Range")
    constructor(cursor: Cursor) : this() {
        id = cursor.getString(cursor.getColumnIndex(ItemBill.ID))
        listItemBillDetail = null
        inforShip = null
        status = cursor.getString(cursor.getColumnIndex(ItemBill.STATUS))
        createDay = cursor.getString(cursor.getColumnIndex(ItemBill.CREATE_DAY))
        billPrice = cursor.getFloat(cursor.getColumnIndex(ItemBill.BILL_PRICE))
    }

    constructor(
        listItemBillDetail: MutableList<PackageProduct>?,
        infoShip: InforShip?,
        status: String,
        createDay: String
    ) : this() {
        this.id = UUID.randomUUID().toString()
        this.listItemBillDetail = listItemBillDetail
        this.inforShip = infoShip
        this.status = status
        this.createDay = createDay
    }

    fun setBillPrice() {
        getAllBillPrice()?.let {
            this.billPrice = it.toFloat()
        }
    }

    fun getAllBillPrice(): Int? = listItemBillDetail?.sumOf { it.getPrice() }

    fun getPrice() = listItemBillDetail?.sumOf { it.getPrice() }

    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID, id)
            put(ID_INFOR_SHIP, inforShip?.id)
            put(STATUS, status)
            put(CREATE_DAY, createDay)
            put(BILL_PRICE, billPrice)
        }
    }

    companion object {
        const val TABLE_NAME: String = "ItemBill"
        const val ID: String = "id"
        const val ID_INFOR_SHIP: String = "idInforShip"
        const val STATUS: String = "status"
        const val CREATE_DAY: String = "createDay"
        const val BILL_PRICE: String = "billPrice"
    }
}