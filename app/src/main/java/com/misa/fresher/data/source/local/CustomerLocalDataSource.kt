package com.misa.fresher.data.source.local

import android.content.Context
import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.source.CustomerDataSource
import com.misa.fresher.data.source.local.dao.CustomerDAO
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
class CustomerLocalDataSource(
    context: Context
) : CustomerDataSource.Local {

    private val customerDAO = CustomerDAO.getInstance(AppDatabase.getInstance(context))

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(action: LoadedAction<List<Customer>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = customerDAO.getAll()
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