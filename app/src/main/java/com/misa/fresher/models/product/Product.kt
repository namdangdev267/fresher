package com.misa.fresher.models.product

import java.io.Serializable

data class Product(
    var name: String = "",
    var code: String = "",
    var category: String = "",
    var date: Long = 0,
    var image: Int = 0,
    var items: ArrayList<ProductItem> = arrayListOf(),
    var units: ArrayList<ProductUnit> = arrayListOf(),
    var unit: ProductUnit = ProductUnit("", 1),
    var amount: Int = 0
): Serializable {
    val quantity get() = items.sumOf { it.quantity }
    val price get() = items[0].price * unit.value * amount
}