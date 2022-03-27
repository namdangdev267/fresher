package com.misa.fresher.ui.sale.bill.deliveryinfo.receiverinfo

import com.misa.fresher.core.BasePresenter

/**
 * Presenter cho màn nhập thông tin người nhận
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
class ReceiverInfoPresenter(
    view: ReceiverInfoContract.View
) : BasePresenter<ReceiverInfoContract.View>(view), ReceiverInfoContract.Presenter {
}