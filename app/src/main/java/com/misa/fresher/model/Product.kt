package com.misa.fresher.model

data class Product(
    var name: String,
    var id: String,
    var price: Float,
    var maxPrice: Float? = null
)
