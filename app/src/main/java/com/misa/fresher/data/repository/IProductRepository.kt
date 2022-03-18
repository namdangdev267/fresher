package com.misa.fresher.data.repository

import com.misa.fresher.data.model.product.Product

/**
 * - interface purpose:
 * 
 * @author HTLong
 * @edit_at 2022-03-18
 */
interface IProductRepository {
    fun getAllProducts(): ArrayList<Product>
}