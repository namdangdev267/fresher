package com.misa.fresher.data.model.product

import java.io.Serializable

data class ProductBill(
    var date: Long,
    var customer: String,
    val products: ArrayList<Product>
) : Serializable {
    companion object { var _id: Int = 1 }
    val id: Int = _id++
}
