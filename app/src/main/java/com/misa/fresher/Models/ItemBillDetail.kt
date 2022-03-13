package com.misa.fresher.Models

class ItemBillDetail(
    var itemSale: ItemSale,
    var name: String,
    var id: String,
    var amount: Int
) {
    init {
        this.name = itemSale.name + "(" + itemSale.color.name + ")"
        this.id = itemSale.id + "-" + itemSale.color.name
    }

    fun getAllPrice(): Float {
        return amount * itemSale.price
    }

    fun updateAmount(num: Int) {
        this.amount += num
    }
}

