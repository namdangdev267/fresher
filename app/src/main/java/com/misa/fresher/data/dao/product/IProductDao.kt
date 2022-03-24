package com.misa.fresher.data.dao.product

import com.misa.fresher.data.model.Products

interface IProductDao {
    suspend fun getAllProducts():MutableList<Products>
    fun addProducts(products: Products) : Long
}