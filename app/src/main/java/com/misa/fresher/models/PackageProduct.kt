package com.misa.fresher.models

class PackageProduct(
    var product: Product,
    var countPackage: Int
) {
    var nameProduct: String = product.nameProduct
    var idProduct: String = product.codeProduct

    fun getPrice(): Int = countPackage * product.priceProduct

    fun updateCount(quantity: Int) {
        this.countPackage += quantity
    }
}