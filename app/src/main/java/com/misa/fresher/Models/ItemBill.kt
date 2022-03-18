package com.misa.fresher.Models

import com.misa.fresher.Models.Enum.BillStatus

data class ItemBill (
    val id:String,
    var listItemBillDetail:MutableList<PackageProduct>,
    var inforShip: InforShip?,
    var status:BillStatus
)
{
    fun getPrice() : Float = listItemBillDetail.map { it.getPrice() }.sum().toFloat()
}