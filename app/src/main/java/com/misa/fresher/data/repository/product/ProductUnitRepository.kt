package com.misa.fresher.data.repository.product

import com.misa.fresher.data.model.product.ProductUnit
import com.misa.fresher.data.source.IProductDataSource

class ProductUnitRepository private constructor(private val productUnitLocalDataSource: IProductDataSource.Local.IUnit) :
    IProductRepository.IUnit {


    override fun insert(productUnit: ProductUnit): Long {
        return productUnitLocalDataSource.insert(productUnit)
    }

    override fun getByModelId(modelId: Int): ArrayList<ProductUnit> {
        return productUnitLocalDataSource.getByModelId(modelId = modelId)
    }

    companion object {
        private var instance: ProductUnitRepository? = null
        fun getInstance(productUnitLocalDataSource: IProductDataSource.Local.IUnit) =
            instance ?: ProductUnitRepository(productUnitLocalDataSource).also { instance = it }
    }

}