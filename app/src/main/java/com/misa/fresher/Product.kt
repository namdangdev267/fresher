package com.misa.fresher

class Product(
    val imgProduct: Int,
    val nameProduct: String,
    val codeProduct: String,
    val priceProduct: Int
) {
    companion object {
        var productId = 0
        fun createProductList(numContacts: Int): ArrayList<Product> {
            val product = ArrayList<Product>()
            for (i in 1..20) {
                product.add(
                    Product(
                        R.drawable.clothes,
                        "Áo Coolmate",
                        "AC" + ++productId,
                        100000 + 10000 * i
                    )
                )
            }
            return product
        }
    }
}