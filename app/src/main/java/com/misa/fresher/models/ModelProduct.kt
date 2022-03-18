package com.misa.fresher.models

import com.misa.fresher.models.enum.Category
import com.misa.fresher.models.enum.Color

data class ModelProduct(
    var name:String,
    var minPrice:Float,
    var maxPrice:Float,
    var id:String,
    var colorList: List<Color>,
    var categoryList: List<Category>,
    var totalQuantity:Int,
    var listItemProduct: List<ItemProduct>
)