package com.misa.fresher.data.model

import android.content.ContentValues
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(
    val id:Int,
    val name : String,
    val number : String,
    val address : String
):Parcelable{
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
