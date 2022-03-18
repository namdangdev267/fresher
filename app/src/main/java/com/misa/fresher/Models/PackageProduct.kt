package com.misa.fresher.Models

class PackageProduct(
    var product: Product,
    var namePackage: String,
    var codePackage: String,
    var countPackage: Int
) {
init {
    this.namePackage = product.nameProduct
    this.codePackage = product.codeProduct
}

    fun getPrice(): Int = countPackage * product.priceProduct

    fun updateCount(quantity: Int){
        this.countPackage += quantity
    }
}