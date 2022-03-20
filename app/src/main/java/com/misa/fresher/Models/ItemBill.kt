package com.misa.fresher.Models

import com.misa.fresher.Models.Enum.BillStatus
import java.util.*

data class ItemBill(
    val id: String,
    var listItemBillDetail: MutableList<PackageProduct>,
    var inforShip: InforShip?,
    var status: BillStatus,
    var dayCreate: Date
)
{
    fun getPrice() = listItemBillDetail.sumOf { it.getPrice() }
}