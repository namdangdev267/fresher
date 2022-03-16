package com.misa.fresher.Models

import com.misa.fresher.Models.Product

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

    fun getPrice(): Float{
        return countPackage * product.priceProduct
    }

    fun updateCount(quantity: Int){
        this.countPackage += quantity
    }
}