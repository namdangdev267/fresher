package com.misa.fresher.ui.main

import android.content.Context
import com.misa.fresher.core.BasePresenter

/**
 * Presenter cho activity main
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
class MainPresenter(
    view: MainContract.View,
    context: Context
) : BasePresenter<MainContract.View>(view, context), MainContract.Presenter