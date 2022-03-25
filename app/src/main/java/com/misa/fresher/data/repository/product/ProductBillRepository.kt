package com.misa.fresher.data.repository.product

import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.data.source.IProductDataSource

class ProductBillRepository private constructor(private val productBillLocalDataSource: IProductDataSource.Local.IBill) :
    IProductRepository.IBill {

    override fun insert(productBill: ProductBill): Long {
        return productBillLocalDataSource.insert(productBill)
    }

    override fun getAll(): ArrayList<ProductBill> {
        return productBillLocalDataSource.getByCol()
    }

    companion object {
        private var instance: ProductBillRepository? = null
        fun getInstance(productBillLocalDataSource: IProductDataSource.Local.IBill) =
            instance ?: ProductBillRepository(productBillLocalDataSource).also { instance = it }
    }
}