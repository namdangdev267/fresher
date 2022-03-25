package com.misa.fresher.data.repository

import android.content.Context
import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.source.ProductDataSource
import com.misa.fresher.data.source.local.ProductLocalDataSource
import com.misa.fresher.util.LoadedAction

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductRepositoryImpl(
    context: Context
) : ProductRepository {

    private val productLocal: ProductDataSource.Local = ProductLocalDataSource(context)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<Product>, action: LoadedAction<Boolean>) {
        productLocal.create(list, action)
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(action: LoadedAction<List<Product>>) {
        productLocal.getAll(action)
    }
}