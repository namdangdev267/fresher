package com.misa.fresher.Models

import com.misa.fresher.Models.Enum.BillStatus

data class ItemBill (
    val id:String,
    var listItemBillDetail:MutableList<ItemBillDetail>,
    var inforShip: InforShip?,
    var status:BillStatus
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