package com.misa.fresher.models

import com.misa.fresher.R

data class Product(
    val productImage : Int,
    val productName :String,
    val productID:String,
    val price : Double){

    companion object{
        fun getListProduct() :ArrayList<Product>{
            var count : Int = 0
            val listProduct = ArrayList<Product>()
            for(i in 1 until 21){
                count++
                listProduct.add(Product(R.mipmap.ic_launcher,"computer "+ i,"id " + i,0.0 + i * (1..20).random()))
            }
            return listProduct
        }
    }
}
