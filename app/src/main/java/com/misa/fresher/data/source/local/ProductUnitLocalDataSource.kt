package com.misa.fresher.data.source.local

import android.content.Context
import com.misa.fresher.data.entity.ProductUnit
import com.misa.fresher.data.source.ProductUnitDataSource
import com.misa.fresher.data.source.local.dao.ProductUnitDAO
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.LoadedAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductUnitLocalDataSource(
    context: Context
) : ProductUnitDataSource.Local {

    private val unitDAO = ProductUnitDAO.getInstance(AppDatabase.getInstance(context))

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<ProductUnit>, action: LoadedAction<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = unitDAO.create(list)
                withContext(Dispatchers.Main) {
                    action.onResponse(result)
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    action.onException(ex)
                }
            }
        }
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(unit: ProductUnit, action: LoadedAction<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = unitDAO.create(unit)
                withContext(Dispatchers.Main) {
                    action.onResponse(result)
                }
            } catch (ex: Exception) {
                action.onException(ex)
            }
        }
    }
}