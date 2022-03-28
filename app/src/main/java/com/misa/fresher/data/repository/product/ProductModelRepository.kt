package com.misa.fresher.data.repository.product

import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.data.source.IProductDataSource

class ProductModelRepository private constructor(private val productModelLocalDataSource: IProductDataSource.Local.IModel) :
    IProductRepository.IModel {

    override fun insert(productModel: ProductModel): Long {
        return productModelLocalDataSource.insert(productModel)
    }

    override fun getAll(): ArrayList<ProductModel> {
        return productModelLocalDataSource.getByCol()
    }

    override fun getById(modelId: Int): ProductModel? {
        val pModels = productModelLocalDataSource.getByCol(ProductModel.ID, modelId.toString())
        return if(pModels.isNotEmpty()) pModels[0] else null
    }

    companion object {
        private var instance: ProductModelRepository? = null
        fun getInstance(productModelLocalDataSource: IProductDataSource.Local.IModel) =
            instance ?: ProductModelRepository(productModelLocalDataSource).also { instance = it }
    }

}