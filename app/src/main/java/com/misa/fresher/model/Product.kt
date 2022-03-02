package com.misa.fresher.model

import com.misa.fresher.R

data class Product(val id: String,val name: String,val price: Double,val img: Int){
    companion object{
        private var lastProductId = 0
        fun createProductsList(numProduct: Int):ArrayList<Product>{
            val products = ArrayList<Product>()
            for (i in 1..numProduct){
                products.add(Product("ABC $i","tui xach $i",1222.0, R.drawable.ic_shopping_bag))
            }
            return products
        }
    }
}