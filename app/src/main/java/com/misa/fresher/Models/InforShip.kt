package com.misa.fresher.Models

import com.misa.fresher.Models.Enum.DepositMethod
import com.misa.fresher.Models.Enum.SaleChannel

data class InforShip (
    var receiver: String?,
    var tel:String?,
    var address:String?,
    var area:String?,
    var ward:String?,
    var shipPaid:Float?,
    var depositMethod: DepositMethod?,
    var deposit:Float?,
    var saleChannel:SaleChannel?,
    var collectCOD:Boolean?
)