package com.misa.fresher.data.source.local

import android.content.Context
import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.source.ProductDataSource
import com.misa.fresher.data.source.local.dao.ProductDAO
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
class ProductLocalDataSource(
    context: Context
) : ProductDataSource.Local {

    private val productDAO = ProductDAO.getInstance(AppDatabase.getInstance(context))

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<Product>, action: LoadedAction<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = productDAO.create(list)
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
    override fun getAll(action: LoadedAction<List<Product>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = productDAO.getAll()
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
}