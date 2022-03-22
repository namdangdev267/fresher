package com.misa.fresher.models

import com.misa.fresher.R

data class Product(
    val productImage : Int,
    val productName :String,
    val productID:String,
    val price : Int,
    val color : String,
    val size : String){

    companion object{
        fun getListProduct() :ArrayList<Product>{
            val listProduct = ArrayList<Product>()
                listProduct.add(Product(R.mipmap.ic_launcher,"bbb","ao001",53,"do","s"))
                listProduct.add(Product(R.mipmap.ic_launcher,"aaa","ao001",123,"do","s"))
                listProduct.add(Product(R.mipmap.ic_launcher,"ccc","ao001",33,"do","s"))
                listProduct.add(Product(R.mipmap.ic_launcher,"ddd","ao001",43,"do","s"))
                listProduct.add(Product(R.mipmap.ic_launcher,"eee","ao001",53,"do","s"))
                listProduct.add(Product(R.mipmap.ic_launcher,"ggg","ao001",93,"do","s"))
                listProduct.add(Product(R.mipmap.ic_launcher,"hhh","ao001",3,"do","s"))
            return listProduct
        }
    }
}
