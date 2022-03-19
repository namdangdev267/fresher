package com.misa.fresher.models

import com.misa.fresher.models.enum.Category
import com.misa.fresher.models.enum.Color

data class ItemProduct(
    var name:String,
    var price:Float,
    var id:String,
    var color: Color,
    var category: Category,
    var availableQuantity:Int,
    var dateArrival:String
    )