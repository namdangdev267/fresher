package com.misa.fresher.data.source.local

import android.content.Context
import com.misa.fresher.data.entity.ProductColor
import com.misa.fresher.data.source.ProductColorDataSource
import com.misa.fresher.data.source.local.dao.ProductColorDAO
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
class ProductColorLocalDataSource(
    context: Context
) : ProductColorDataSource.Local {

    private val colorDAO = ProductColorDAO.getInstance(AppDatabase.getInstance(context))

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<ProductColor>, action: LoadedAction<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = colorDAO.create(list)
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
    override fun create(color: ProductColor, action: LoadedAction<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = colorDAO.create(color)
                withContext(Dispatchers.Main) {
                    action.onResponse(result)
                }
            } catch (ex: Exception) {
                action.onException(ex)
            }
        }
    }
}