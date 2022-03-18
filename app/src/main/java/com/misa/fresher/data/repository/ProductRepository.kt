package com.misa.fresher.data.repository

import com.misa.fresher.data.model.product.Product
import com.misa.fresher.global.FakeData

class ProductRepository private constructor() : IProductRepository {
    private var instance: ProductRepository? = null

    fun getInstance(): ProductRepository {
        if(instance == null) instance = ProductRepository()
        return instance!!
    }

    override fun getAllProducts(): ArrayList<Product> {
        return FakeData.products
    }
}