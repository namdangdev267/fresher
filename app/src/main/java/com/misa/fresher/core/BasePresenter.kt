package com.misa.fresher.core

import android.content.Context
import com.misa.fresher.data.DataManager

/**
 * Base class cho các presenter, truyền vào contract.view
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 2
 * @updated 3/21/2022: Tạo class
 * @updated 3/25/2022: Thêm trường [dataManager] là đối tượng chung để thực hiện trao đổi dữ liệu với local database, server
 */
open class BasePresenter<V: BaseContract.View>(
    protected val view: V,
    context: Context
) {
    protected val dataManager = DataManager.getInstance(context)
}