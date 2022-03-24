package com.misa.fresher.models

class ItemBillDetail(var itemProduct: ItemProduct, var quantity: Int) {

    var name: String = itemProduct.name + "(" + itemProduct.color.name.lowercase() + ")"
    var code: String = itemProduct.code + "-" + itemProduct.color.name.lowercase()

    fun getAllPrice() = quantity * itemProduct.price


}

