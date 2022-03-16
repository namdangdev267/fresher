package com.misa.fresher.Models

import com.misa.fresher.Models.Enum.BillStatus
import java.util.*

data class ItemBill (
    val id:String,
    var listItemBillDetail:MutableList<ItemBillDetail>,
    var inforShip: InforShip?,
    var status:BillStatus,
    var createDay:Date
)
{
    fun getPrice():Float
    {
        var res=0f
        for(i in listItemBillDetail)
        {
            res+= i.getAllPrice()
        }
        return res
    }
}