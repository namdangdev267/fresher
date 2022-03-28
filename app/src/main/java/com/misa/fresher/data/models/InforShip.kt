package com.misa.fresher.data.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class InforShip() : Parcelable {

    var id: String = ""
    var receiver: String? = null
    var tel: String? = null
    var address: String? = null
    var area: String? = null

    constructor(receiver: String?, tel: String?, address: String?, area: String?) : this() {
        this.id = UUID.randomUUID().toString()
        this.receiver = receiver
        this.tel = tel
        this.address = address
        this.area = area
    }

    @SuppressLint("Range")
    constructor(cursor: Cursor) : this() {
        this.id = cursor.getString(cursor.getColumnIndex(ID))
        this.receiver = cursor.getString(cursor.getColumnIndex(RECEIVER))
        this.tel = cursor.getString(cursor.getColumnIndex(TEL))
        this.address = cursor.getString(cursor.getColumnIndex(ADDRESS))
        this.area = cursor.getString(cursor.getColumnIndex(AREA))
    }

    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID, id)
            put(RECEIVER, receiver)
            put(TEL, TEL)
            put(ADDRESS, address)
            put(AREA, area)
        }
    }

    companion object {
        const val TABLE_NAME: String = "ITEM_PRODUCT_TABLE"
        const val ID: String = "id"
        const val RECEIVER: String = "name"
        const val TEL: String = "name"
        const val ADDRESS: String = "name"
        const val AREA: String = "name"

    }

}