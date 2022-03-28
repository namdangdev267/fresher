package com.misa.fresher.ui.main

import com.misa.fresher.core.BaseContract

/**
 * Contract cho activity main, truyền vào contract.view
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
interface MainContract {

    interface View: BaseContract.View

    interface Presenter: BaseContract.Presenter
}