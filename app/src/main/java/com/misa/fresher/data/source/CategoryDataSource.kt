package com.misa.fresher.data.source

import com.misa.fresher.data.entity.Category
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy thông tin danh mục hàng hóa
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface CategoryDataSource {

    interface Local {
        fun create(list: List<Category>, action: LoadedAction<Boolean>)
        fun getAll(action: LoadedAction<List<Category>>)
    }

    interface Remote
}