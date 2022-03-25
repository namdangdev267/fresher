package com.misa.fresher.data.repository

import android.content.Context
import com.misa.fresher.data.entity.ProductColor
import com.misa.fresher.data.source.ProductColorDataSource
import com.misa.fresher.data.source.local.ProductColorLocalDataSource
import com.misa.fresher.util.LoadedAction

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductColorRepositoryImpl(
    context: Context
) : ProductColorRepository {

    private val colorLocal: ProductColorDataSource.Local = ProductColorLocalDataSource(context)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<ProductColor>, action: LoadedAction<Boolean>) {
        colorLocal.create(list, action)
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(color: ProductColor, action: LoadedAction<Boolean>) {
        colorLocal.create(color, action)
    }
}