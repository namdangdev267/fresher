package com.misa.fresher.models.product

import java.io.Serializable

data class ProductItem(
    val size: String,
    val color: String,
    var price: Double,
    var quantity: Int,
    var amount: Int = 0,
): Serializable