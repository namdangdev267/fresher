package com.misa.fresher.Models

import com.misa.fresher.R

class Product(
    val imgProduct: Int,
    val nameProduct: String,
    val codeProduct: String,
    val priceProduct: Float,
    var quantity: Int,
//    val type: ProductType
)

//{
//    companion object {
//        var productId = 0
//        fun createProductList(numContacts: Int): ArrayList<Product> {
//            val product = ArrayList<Product>()
//            for (i in 1..20) {
//                product.add(
//                    Product(
//                        R.drawable.clothes,
//                        i.toString() + "Test " + i.toString(),
//                        "AC" + ++productId,
//                        100000F + 10000 * i, 10
//                    )
//                )
//            }
//            return product
//        }
//    }
//}