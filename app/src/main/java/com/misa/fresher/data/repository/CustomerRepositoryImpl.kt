package com.misa.fresher.data.repository

import android.content.Context
import com.misa.fresher.data.entity.Customer
import com.misa.fresher.data.source.CustomerDataSource
import com.misa.fresher.data.source.local.CustomerLocalDataSource
import com.misa.fresher.util.LoadedAction

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class CustomerRepositoryImpl(
    context: Context
) : CustomerRepository {

    private val customerLocal: CustomerDataSource.Local = CustomerLocalDataSource(context)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun create(list: List<Customer>, action: LoadedAction<Boolean>) {
        customerLocal.create(list, action)
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(action: LoadedAction<List<Customer>>) {
        customerLocal.getAll(action)
    }
}