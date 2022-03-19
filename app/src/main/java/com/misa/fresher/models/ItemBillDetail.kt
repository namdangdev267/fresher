package com.misa.fresher.models

class ItemBillDetail(var itemProduct: ItemProduct, var quantity: Int) {

    var name: String = itemProduct.name + "(" + itemProduct.color.name.lowercase() + ")"
    var id: String = itemProduct.id + "-" + itemProduct.color.name.lowercase()

    fun getAllPrice() = quantity * itemProduct.price
    fun updateQuantity(num: Int) {
        this.quantity += num
    }

}

