package com.misa.fresher.data.source.local

import android.content.Context
import com.misa.fresher.data.entity.Category
import com.misa.fresher.data.source.CategoryDataSource
import com.misa.fresher.data.source.local.dao.CategoryDAO
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
class CategoryLocalDataSource(
    context: Context
): CategoryDataSource.Local {

    private val categoryDAO = CategoryDAO.getInstance(AppDatabase.getInstance(context))

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(action: LoadedAction<List<Category>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = categoryDAO.getAll()
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