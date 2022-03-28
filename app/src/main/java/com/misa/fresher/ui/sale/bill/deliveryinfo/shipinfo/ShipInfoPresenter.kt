package com.misa.fresher.ui.sale.bill.deliveryinfo.shipinfo

import android.content.Context
import com.misa.fresher.core.BasePresenter

/**
 * Presenter cho màn hình nhập thông tin đơn vị vận chuyển
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
class ShipInfoPresenter(
    view: ShipInfoContract.View,
    context: Context
) : BasePresenter<ShipInfoContract.View>(view, context), ShipInfoContract.Presenter