package com.misa.fresher.data.repository

import android.content.Context
import com.misa.fresher.data.entity.Category
import com.misa.fresher.data.source.CategoryDataSource
import com.misa.fresher.data.source.local.CategoryLocalDataSource
import com.misa.fresher.util.LoadedAction

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class CategoryRepositoryImpl(
    context: Context
) : CategoryRepository {

    private val categoryLocal: CategoryDataSource.Local = CategoryLocalDataSource(context)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getAll(action: LoadedAction<List<Category>>) {
        categoryLocal.getAll(action)
    }
}