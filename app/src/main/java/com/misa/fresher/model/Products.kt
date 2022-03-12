package com.misa.fresher.model

import com.misa.fresher.R

data class Products(val id: String, val name: String, val price: Double, val img: Int){
    companion object{
        fun createProductsList(numProduct: Int):ArrayList<Products>{
            val products = ArrayList<Products>()
            for (i in 1..numProduct){
                products.add(Products("ABC$i","tui xach $i",1222.0+i*10, R.drawable.ic_shopping_bag))
            }
            return products
        }
    }
}