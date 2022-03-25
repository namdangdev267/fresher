package com.misa.fresher.data.repository

import android.content.Context
import com.misa.fresher.data.entity.ProductUnit
import com.misa.fresher.data.source.ProductUnitDataSource
import com.misa.fresher.data.source.local.ProductUnitLocalDataSource
import com.misa.fresher.util.LoadedAction

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductUnitRepositoryImpl(
    context: Context
) : ProductUnitRepository {

    private val unitLocal: ProductUnitDataSource.Local = ProductUnitLocalDataSource(context)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<ProductUnit>, action: LoadedAction<Boolean>) {
        unitLocal.create(list, action)
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(unit: ProductUnit, action: LoadedAction<Boolean>) {
        unitLocal.create(unit, action)
    }
}