package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.models.Product

/**
 * Mục địch sử dụng:
 * Sử dụng khi:
 * @created_by Truong Trung Kien on 3/25/2022
 **/
interface IProductDao {

    suspend fun getAllProducts(): MutableList<Product>
    suspend fun addProduct(product: Product): Long
    suspend fun getProductID(id: Int): Product?
}
