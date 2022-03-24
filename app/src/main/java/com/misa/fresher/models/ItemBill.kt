package com.misa.fresher.models

import com.misa.fresher.models.enums.BillStatus
import java.util.*

data class ItemBill (
    val id:String,
    var listItemBillDetail:MutableList<ItemBillDetail>,
    var infoShip: InfoShip?,
    var status:BillStatus,
    var createDay:Date
)
{
    fun getPrice() = listItemBillDetail.map { it.getAllPrice() }.sum()
}