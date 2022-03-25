package com.misa.fresher.data.source

import com.misa.fresher.data.entity.ProductUnit
import com.misa.fresher.util.LoadedAction

/**
 * Định nghĩa các nguồn dữ liệu để lấy đơn vị mua của sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/24/2022
 *
 * @version 1
 * @updated 3/24/2022: Tạo class
 */
interface ProductUnitDataSource {

    interface Local {
        fun create(list: List<ProductUnit>, action: LoadedAction<Boolean>)
        fun create(unit: ProductUnit, action: LoadedAction<Boolean>)
    }

    interface Remote
}