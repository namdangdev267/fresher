package com.misa.fresher.data.model.product

import java.io.Serializable

data class ProductItem(
    val size: String,
    val color: String,
    var price: Double,
    var quantity: Int
): Serializable