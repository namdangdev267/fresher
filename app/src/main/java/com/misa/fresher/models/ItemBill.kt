package com.misa.fresher.models

import com.misa.fresher.models.enum.BillStatus
import java.util.*

data class ItemBill (
    val id:String,
    var listItemBillDetail:MutableList<ItemBillDetail>,
    var inforShip: InforShip?,
    var status:BillStatus,
    var createDay:Date
)
{
    fun getPrice() = listItemBillDetail.map { it.getAllPrice() }.sum()
}