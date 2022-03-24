package com.misa.fresher.data.model

import android.content.ContentValues
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
* Tạo class dùng để lưu bill
* @Auther : NTBao
* @date : 3/18/2022
**/
@Parcelize
data class Bill(
    val id: Int,
    var listSelectedProduct: MutableList<SelectedProducts>?,
    var customer: Customer?,
    var date : String,
    var totalPrice : Double
):Parcelable{

    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID,id)
            put(ID_CUSTOMER,customer?.id)
            put(DATE,date)
            put(TOTAL_PRICE,totalPrice)
        }
    }
    companion object {
        const val TABLE_NAME : String = "BILL"
        const val ID : String = "id"
        const val ID_CUSTOMER : String = "idCustomer"
        const val TOTAL_PRICE : String = "totalPrice"
        const val DATE : String = "date"
    }
}