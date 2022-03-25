package com.misa.fresher.data.source

import com.misa.fresher.data.entity.Product
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy thông tin sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductDataSource {

    interface Local {
        fun create(list: List<Product>, action: LoadedAction<Boolean>)
        fun getAll(action: LoadedAction<List<Product>>)
    }

    interface Remote
}