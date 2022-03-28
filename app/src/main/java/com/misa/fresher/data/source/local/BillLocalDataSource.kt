package com.misa.fresher.data.source.local

import android.content.Context
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.data.source.BillDataSource
import com.misa.fresher.data.source.local.dao.BillDAO
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
class BillLocalDataSource(
    context: Context
) : BillDataSource.Local {

    private val billDAO = BillDAO.getInstance(AppDatabase.getInstance(context))

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(bill: Bill, action: LoadedAction<Boolean>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = billDAO.create(bill)
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
    override fun getAll(action: LoadedAction<List<Bill>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = billDAO.getAll()
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
    override fun getMaxId(action: LoadedAction<Long>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = billDAO.getMaxId()
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