package com.misa.fresher.Models

import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color

data class ItemProduct(
    var name:String,
    var price:Float,
    var id:String,
    var color: Color,
    var category: Category,
    var quantity:Int
    )