package com.misa.fresher.data.dao.itemproduct

import com.misa.fresher.models.ItemProduct

interface IItemProductDao {
    suspend fun getAllProducts():MutableList<ItemProduct>
    suspend fun addProduct(itemProduct: ItemProduct) : Long
    suspend fun getProductWithID(id:Int) :ItemProduct?
}