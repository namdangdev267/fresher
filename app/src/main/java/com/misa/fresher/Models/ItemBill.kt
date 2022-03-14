package com.misa.fresher.Models

import com.misa.fresher.Models.Enum.BillStatus

data class ItemBill (
    val id:String,
    var listItemBillDetail:MutableList<ItemBillDetail>,
    var price:Float,
    var status:BillStatus
)