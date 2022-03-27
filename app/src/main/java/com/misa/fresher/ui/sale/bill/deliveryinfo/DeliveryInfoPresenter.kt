package com.misa.fresher.ui.sale.bill.deliveryinfo

import com.misa.fresher.core.BasePresenter

/**
 * Presenter cho màn hình thông tin giao hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
class DeliveryInfoPresenter(
    view: DeliveryInfoContract.View
) : BasePresenter<DeliveryInfoContract.View>(view), DeliveryInfoContract.Presenter {
}