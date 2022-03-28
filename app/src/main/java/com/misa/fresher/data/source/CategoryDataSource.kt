package com.misa.fresher.data.source

import com.misa.fresher.data.entity.Category
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy thông tin danh mục hàng hóa
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 2
 * @updated 3/24/2022: Tạo class
 * @updated 3/28/2022: Loại bỏ hàm createList do không có nhu cầu sử dụng
 */
interface CategoryDataSource {

    interface Local {
        fun getAll(action: LoadedAction<List<Category>>)
    }

    interface Remote
}