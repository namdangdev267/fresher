package com.misa.fresher.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.graphics.Color
import android.os.Parcelable
import android.util.Log
import com.misa.fresher.R
import com.misa.fresher.getNumString
import com.misa.fresher.models.enums.BillStatus
import kotlinx.android.parcel.Parcelize

import java.util.*

@Parcelize
class ItemBill() : Parcelable {
    var id: String = ""
    var listItemBillDetail: MutableList<ItemBillDetail>? = mutableListOf()
    var infoShip: InfoShip? = null
    var status: String = ""
    var createDay: String = ""
    var billPrice: Float = 0f

    val idForView: String
        get() = id.getNumString()

    val infoShipForView: String
        get() = if (infoShip?.receiver != null && infoShip?.tel != null) (infoShip?.receiver + "-" + infoShip?.tel) else "Chưa có thông tin"

    val colorInfoShipForView: Int
        get() = if (infoShip?.receiver != null && infoShip?.tel != null) Color.parseColor("#FF000000") else Color.parseColor(
            "#99000000"
        )

    val billPriceForList: String
        get() = billPrice.toString()

    @SuppressLint("Range")
    constructor(cursor: Cursor) : this() {
        id = cursor.getString(cursor.getColumnIndex(ItemBill.ID))
        listItemBillDetail = null
        infoShip = null
        status = cursor.getString(cursor.getColumnIndex(ItemBill.STATUS))
        createDay = cursor.getString(cursor.getColumnIndex(ItemBill.CREATE_DAY))
        billPrice = cursor.getFloat(cursor.getColumnIndex(ItemBill.BILL_PRICE))
    }

    constructor(
        id: String,
        listItemBillDetail: MutableList<ItemBillDetail>?,
        infoShip: InfoShip?,
        status: String,
        createDay: String,
        billPrice: Float
    ) : this() {
        this.id = id
        this.listItemBillDetail = listItemBillDetail
        this.infoShip = infoShip
        this.status = status
        this.createDay = createDay
        this.billPrice = billPrice
    }

    constructor(
        listItemBillDetail: MutableList<ItemBillDetail>?,
        infoShip: InfoShip?,
        status: String,
        createDay: String
    ) : this() {
        this.id = UUID.randomUUID().toString()
        this.listItemBillDetail = listItemBillDetail
        this.infoShip = infoShip
        this.status = status
        this.createDay = createDay
    }

    fun setBillPrice() {
        getAllBillPrice()?.let {
            this.billPrice = it
        }
    }

    fun getAllBillPrice(): Float? = listItemBillDetail?.map { it.getAllPrice() }?.sum()


    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID, id)
            put(ID_INFO_SHIP, infoShip?.id)
            put(STATUS, status)
            put(CREATE_DAY, createDay)
            put(BILL_PRICE, billPrice)
        }
    }

    companion object {
        const val TABLE_NAME: String = "ITEM_BILL_TABLE"
        const val ID: String = "id"
        const val ID_INFO_SHIP: String = "idInfoShip"
        const val STATUS: String = "status"
        const val CREATE_DAY: String = "createDay"
        const val BILL_PRICE: String = "billPrice"
    }
}