package com.misa.fresher.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.misa.fresher.models.enums.DepositMethod
import com.misa.fresher.models.enums.SaleChannel
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class InfoShip() : Parcelable {

    var id: String = UUID.randomUUID().toString()
    var receiver: String? = null
    var tel: String? = null
    var address: String? = null
    var area: String? = null

    @SuppressLint("Range")
    constructor(cursor: Cursor) : this() {
        this.id = cursor.getString(cursor.getColumnIndex(InfoShip.ID))
        this.receiver = cursor.getString(cursor.getColumnIndex(InfoShip.RECEIVER))
        this.tel = cursor.getString(cursor.getColumnIndex(InfoShip.TEL))
        this.address = cursor.getString(cursor.getColumnIndex(InfoShip.ADDRESS))
        this.area = cursor.getString(cursor.getColumnIndex(InfoShip.AREA))
    }

    constructor(receiver: String?, tel: String?, address: String?, area: String?) : this() {
        this.id = UUID.randomUUID().toString()
        this.receiver = receiver
        this.tel = tel
        this.address = address
        this.area = area
    }

    constructor(
        id: String,
        receiver: String?,
        tel: String?,
        address: String?,
        area: String?
    ) : this() {
        this.id = UUID.randomUUID().toString()
        this.receiver = receiver
        this.tel = tel
        this.address = address
        this.area = area
    }

    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID, id)
            put(RECEIVER, receiver)
            put(TEL, tel)
            put(ADDRESS, address)
            put(AREA, area)
        }
    }

    companion object {
        const val TABLE_NAME: String = "INFO_SHIP_TABLE"
        const val ID: String = "id"
        const val RECEIVER: String = "receiver"
        const val TEL: String = "tel"
        const val ADDRESS: String = "address"
        const val AREA: String = "area"

    }


}