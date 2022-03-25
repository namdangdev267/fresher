package com.misa.fresher.data.source.local

import android.content.Context
import com.misa.fresher.data.entity.ProductSize
import com.misa.fresher.data.source.ProductSizeDataSource
import com.misa.fresher.data.source.local.dao.ProductSizeDAO
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.LoadedAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductSizeLocalDataSource(
    context: Context
) : ProductSizeDataSource.Local {

    private val sizeDAO = ProductSizeDAO.getInstance(AppDatabase.getInstance(context))

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<ProductSize>, action: LoadedAction<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = sizeDAO.create(list)
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
    override fun create(size: ProductSize, action: LoadedAction<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = sizeDAO.create(size)
                withContext(Dispatchers.Main) {
                    action.onResponse(result)
                }
            } catch (ex: Exception) {
                action.onException(ex)
            }
        }
    }
}