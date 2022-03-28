package com.misa.fresher.ui.sale.bill.deliveryinfo.packageinfo

import android.content.Context
import com.misa.fresher.core.BasePresenter

/**
 * Presenter cho màn nhập thông tin gói hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
class PackageInfoPresenter(
    view: PackageInfoContract.View,
    context: Context
) : BasePresenter<PackageInfoContract.View>(view, context), PackageInfoContract.Presenter