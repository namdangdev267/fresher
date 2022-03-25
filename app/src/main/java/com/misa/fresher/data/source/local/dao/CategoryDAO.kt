package com.misa.fresher.data.source.local.dao

import com.misa.fresher.core.BaseDAO
import com.misa.fresher.data.entity.Category
import com.misa.fresher.data.source.local.database.AppDatabase

/**
 * Dao để trao đổi dữ liệu với bảng Category
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface CategoryDAO : BaseDAO<Category> {

    companion object {
        private var dao: CategoryDAO? = null

        fun getInstance(appDatabase: AppDatabase) =
            dao ?: CategoryDAOImpl(appDatabase).also { dao = it }
    }
}