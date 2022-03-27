package com.misa.fresher.data.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(
    var id: Int?,
    var name: String?,
    var number: String?,
    var address: String?
):Parcelable{

    @SuppressLint("Range")
    constructor(cursor: Cursor):this(0,"","",""){
        id = cursor.getInt(cursor.getColumnIndex(ID))
        name = cursor.getString(cursor.getColumnIndex(NAME))
        number = cursor.getString(cursor.getColumnIndex(NUMBER))
        address = cursor.getString(cursor.getColumnIndex(ADDRESS))
    }

    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID,id)
            put(NAME,name)
            put(NUMBER,number)
            put(ADDRESS,address)
        }
    }
    companion object {
        const val TABLE_NAME : String = "CUSTOMER"
        const val ID : String = "id"
        const val NAME : String = "name"
        const val NUMBER : String = "number"
        const val ADDRESS : String = "address"
    }
}
