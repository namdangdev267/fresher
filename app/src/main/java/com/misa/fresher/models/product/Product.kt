package com.misa.fresher.models.product

import com.misa.fresher.utils.Utils
import java.io.Serializable

data class Product(
    var name: String,
    var code: String,
    var category: String = "",
    var date: Long = 0,
    var image: Int = 0,
    var items: ArrayList<ProductItem>,
    var units: ArrayList<ProductUnit>,
    var unit: ProductUnit = ProductUnit("", 1),
    var isUnitSelect: Boolean = false
): Serializable {
    fun quantity() = items.sumOf { it.quantity }
    fun getAmount() = items.sumOf { it.amount }
    fun getColors() = Utils.listToArrayList(items.map { it.color }.distinct())
    fun getPrice(color: String? = null, size: String? = null): String {
        items.filter {
            (if (color == null) true else it.color == color) && (if (size == null) true else it.size == size)
        }.apply {
            return if (this.size == 1) (this[0].price * unit.value).toString()
            else {
                "${this.minOf { it.price } * unit.value} ~ ${this.maxOf { it.price } * unit.value}"
            }
        }
    }

    fun getTotalPrice() = items.sumOf { it.price * it.amount * unit.value }

    fun getSizes(color: String?) = Utils.listToArrayList(items.filter { color == null || it.color == color }.map { it.size }.distinct())

    fun getUnitNames() = Utils.listToArrayList(units.map { it.name }.distinct())
}