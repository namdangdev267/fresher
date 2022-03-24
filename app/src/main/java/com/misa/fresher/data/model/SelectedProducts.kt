package com.misa.fresher.data.model

import android.content.ContentValues
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
* Tạo class SelectedProducts lưu product chọn và số lượng của nó
* @Auther : NTBao
* @date : 3/18/2022
**/
@Parcelize
data class SelectedProducts(
    var amonut : Int,
    val product : Products
):Parcelable{

    companion object{
        const val TABLE_NAME : String = "SELECTEDPRODUCT"
        const val ID : String = "id"
        const val ID_PRODUCT : String = "idProduct"
        const val ID_BILL : String = "idBill"
        const val AMONUT : String = "amount"
    }
}