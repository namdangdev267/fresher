package com.misa.fresher.data.product
import com.misa.fresher.model.Product
interface IProductDAO {
     suspend fun selectAllProduct():ArrayList<Product>
     suspend fun addProduct(product: Product):Boolean
}