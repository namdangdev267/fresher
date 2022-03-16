package com.misa.fresher.Models

import com.misa.fresher.Models.Enum.Category
import com.misa.fresher.Models.Enum.Color

data class ModelProduct(
    var name:String,
    var minPrice:Float,
    var maxPrice:Float,
    var id:String,
    var colorList: List<Color>,
    var categoryList: List<Category>,
    var totalQuantity:Int,
    var listItemProduct: List<Product>
)