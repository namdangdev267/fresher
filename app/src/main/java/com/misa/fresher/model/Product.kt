package com.misa.fresher.model

data class Product(
    var name: String,
    var id: String,
    var price: Double,
    var maxPrice: Double? = null
)
