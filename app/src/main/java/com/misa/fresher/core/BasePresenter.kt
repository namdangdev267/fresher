package com.misa.fresher.core

import com.misa.fresher.data.DataManager

/**
 * Base class cho các presenter, truyền vào contract.view
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
open class BasePresenter<V: BaseContract.View>(
    protected val view: V
) {
    protected val dataManager = DataManager.getInstance()
}