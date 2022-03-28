package com.misa.fresher.models

import com.misa.fresher.R

data class Product(
    val productId : Int,
    val productImage : Int,
    val productName :String,
    val productCode:String,
    val price : Int,
    val color : String,
    val size : String){

    companion object{
        fun getListProduct() :ArrayList<Product>{
            val listProduct = ArrayList<Product>()
                listProduct.add(Product(1,R.mipmap.ic_launcher,"bbb","ao001",53,"do","s"))
                listProduct.add(Product(2,R.mipmap.ic_launcher,"aaa","ao002",123,"do","s"))
                listProduct.add(Product(3,R.mipmap.ic_launcher,"ccc","ao003",33,"do","s"))
                listProduct.add(Product(4,R.mipmap.ic_launcher,"ddd","ao004",43,"do","s"))
                listProduct.add(Product(5,R.mipmap.ic_launcher,"eee","ao005",53,"do","s"))
                listProduct.add(Product(6,R.mipmap.ic_launcher,"ggg","ao006",93,"do","s"))
                listProduct.add(Product(7,R.mipmap.ic_launcher,"hhh","ao007",3,"do","s"))
            return listProduct
        }
    }
}
