package com.misa.fresher.Models

class ItemBillDetail(
    var itemProduct: ItemProduct,
    var name: String,
    var id: String,
    var quantity: Int
) {
    init {
        this.name = itemProduct.name + "(" + itemProduct.color.name + ")"
        this.id = itemProduct.id + "-" + itemProduct.color.name
    }

    fun getAllPrice(): Float {
        return quantity * itemProduct.price
    }

    fun updateQuantity(num: Int) {
        this.quantity += num
    }
}

