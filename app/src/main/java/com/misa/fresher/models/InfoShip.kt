package com.misa.fresher.models

import com.misa.fresher.models.enums.DepositMethod
import com.misa.fresher.models.enums.SaleChannel

data class InfoShip (
    var receiver: String?,
    var tel:String?,
    var address:String?,
    var area:String?,
//    var ward:String?,
//    var shipPaid:Float?,
//    var depositMethod: DepositMethod?,
//    var deposit:Float?,
//    var saleChannel:SaleChannel?,
//    var collectCOD:Boolean?
)