package com.misa.fresher.data.repository

import android.content.Context
import com.misa.fresher.data.entity.ProductSize
import com.misa.fresher.data.source.ProductSizeDataSource
import com.misa.fresher.data.source.local.ProductSizeLocalDataSource
import com.misa.fresher.util.LoadedAction

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductSizeRepositoryImpl(
    context: Context
) : ProductSizeRepository {

    private val sizeLocal: ProductSizeDataSource.Local = ProductSizeLocalDataSource(context)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<ProductSize>, action: LoadedAction<Boolean>) {
        sizeLocal.create(list, action)
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(size: ProductSize, action: LoadedAction<Boolean>) {
        sizeLocal.create(size, action)
    }
}