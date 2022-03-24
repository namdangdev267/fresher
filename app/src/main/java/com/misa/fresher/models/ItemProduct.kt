package com.misa.fresher.models

import com.misa.fresher.models.enums.Category
import com.misa.fresher.models.enums.Color

data class ItemProduct(
    var name:String,
    var price:Float,
    var code:String,
    var color: Color,
    var category: Category,
    var availableQuantity:Int,
    var dateArrival:String
    )