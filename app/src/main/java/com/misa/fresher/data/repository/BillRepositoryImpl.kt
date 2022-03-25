package com.misa.fresher.data.repository

import android.content.Context
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.data.source.BillDataSource
import com.misa.fresher.data.source.local.BillLocalDataSource
import com.misa.fresher.util.LoadedAction

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class BillRepositoryImpl(
    context: Context
) : BillRepository {

    private val billLocal: BillDataSource.Local = BillLocalDataSource(context)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(bill: Bill, action: LoadedAction<Boolean>) {
        billLocal.create(bill, action)
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(action: LoadedAction<List<Bill>>) {
        billLocal.getAll(action)
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getMaxId(action: LoadedAction<Long>) {
        billLocal.getMaxId(action)
    }
}