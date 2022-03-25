package com.misa.fresher.data.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
* Tạo class Products lấy từ fake data
* @Auther : NTBao
* @date : 3/18/2022
**/
@Parcelize
data class Products(
    var id : Int,
    var code: String,
    var name: String,
    var price: Double,
    var img: Int,
    var color: String,
    var size: String
):Parcelable{

    fun getContentValues():ContentValues{
        return ContentValues().apply {
            put(CODE,code)
            put(NAME,name)
            put(PRICE,price)
            put(IMG,img)
            put(COLOR,color)
            put(SIZE,size)
        }
    }
    companion object {
        const val TABLE_NAME : String = "PRODUCT"
        const val ID : String = "id"
        const val CODE : String = "code"
        const val NAME : String = "name"
        const val PRICE : String = "price"
        const val IMG : String = "img"
        const val COLOR : String = "color"
        const val SIZE : String = "size"
    }
}