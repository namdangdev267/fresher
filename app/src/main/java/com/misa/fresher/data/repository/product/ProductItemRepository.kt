package com.misa.fresher.data.repository.product

import com.misa.fresher.data.model.product.ProductItem
import com.misa.fresher.data.source.IProductDataSource

class ProductItemRepository private constructor(private val productItemLocalDataSource: IProductDataSource.Local.IItem) :
    IProductRepository.IItem {

    override fun insert(productItem: ProductItem): Long {
        return productItemLocalDataSource.insert(productItem)
    }

    override fun getById(itemId: Int): ProductItem? {
        val pItems = productItemLocalDataSource.getByCol(ProductItem.ID, itemId.toString())
        return if(pItems.isNotEmpty()) pItems[0] else null
    }

    override fun getByModelId(modelId: Int): ArrayList<ProductItem> {
        return productItemLocalDataSource.getByCol(ProductItem.MODEL_ID, modelId.toString())
    }

    companion object {
        private var instance: ProductItemRepository? = null
        fun getInstance(productItemLocalDataSource: IProductDataSource.Local.IItem) =
            instance ?: ProductItemRepository(productItemLocalDataSource).also { instance = it }
    }


}