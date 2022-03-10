package com.misa.fresher.models

data class Product(
    val name: String,
    val code: String,
    val price: Double
)

data class ProductModel(
    val name: String,
    val code: String,
    val type: String,
    val date: Long,
    val items: ArrayList<ProductItem>,
    val units: ArrayList<Unit>,
) {
    val quantity: Int
        get() = items.sumOf { it.quantity }
    val price: String
        get() = items.joinToString(" ~ ") { it.price.toString() }
}

data class ProductItem(
    val size: String,
    val color: String,
    val price: Double,
    val unit: ProductUnit,
    var quantity: Int,
)

data class ProductUnit(
    val name: String,
    val value: Int
)