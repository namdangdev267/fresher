package com.misa.fresher.global

import com.misa.fresher.R
import com.misa.fresher.models.product.Product
import com.misa.fresher.models.product.ProductItem
import com.misa.fresher.models.product.ProductUnit

object FakeData {
    private var _products: ArrayList<Product>? = null
    val products: ArrayList<Product>
        get() {
            if (_products == null) {
                val items1: ArrayList<ProductItem> = arrayListOf(
                    ProductItem("S", "red", 1000.0, 10),
                    ProductItem("L", "red", 3000.0, 10),
                    ProductItem("M", "red", 3000.0, 10),
                )
                val items2: ArrayList<ProductItem> = arrayListOf(
                    ProductItem("XL", "black", 1600.0, 0),
                    ProductItem("M", "black", 2600.0, 0),
                    ProductItem("S", "black", 3600.0, 0),
                )
                val items3: ArrayList<ProductItem> = arrayListOf(
                    ProductItem("XXL", "blue", 1000.0, 0),
                    ProductItem("L", "blue", 3200.0, 12),
                    ProductItem("S", "blue", 1600.0, 0),
                    ProductItem("M", "blue", 2600.0, 3),
                )
                val units = arrayListOf(
                    ProductUnit("Piece", 1),
                    ProductUnit("Pair", 2),
                    ProductUnit("Set", 3),
                    ProductUnit("Dozen", 12)
                )
                val unit = units[0]
                val image = R.drawable.ic_product
                _products = arrayListOf()
                for (i in 5 downTo 1) _products!!.add(
                    Product("name $i", "code$i", "type${i / 2}", i * 1000L, image, items1, units, unit)
                )
                for (i in 15 downTo 10) _products!!.add(
                    Product("name $i", "code$i", "type${i / 4}", i * 1000L, image, items3, units, unit)
                )
                for (i in 10 downTo 5) _products!!.add(
                    Product("name $i", "code$i", "type${i / 3}", i * 1000L, image, items2, units, unit)
                )
            }
            return _products!!
        }
}