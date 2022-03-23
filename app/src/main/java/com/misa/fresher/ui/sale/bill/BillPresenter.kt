package com.misa.fresher.ui.sale.bill

import com.misa.fresher.core.BasePresenter

/**
 * Presenter cho màn hình thanh toán
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
class BillPresenter(
    view: BillContract.View
) : BasePresenter<BillContract.View>(view), BillContract.Presenter {
}