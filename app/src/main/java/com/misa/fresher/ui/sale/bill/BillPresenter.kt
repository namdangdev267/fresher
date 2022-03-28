package com.misa.fresher.ui.sale.bill

import android.content.Context
import com.misa.fresher.common.RandomSingleton
import com.misa.fresher.core.BasePresenter
import com.misa.fresher.data.entity.Bill

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
    view: BillContract.View,
    context: Context
) : BasePresenter<BillContract.View>(view, context), BillContract.Presenter {

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getNextId() {
        dataManager.getMaxIdBill()
            .onSuccess {
                view.getNextIdSuccess(it + 1)
            }
            .call()
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun randomCustomer() {
        dataManager.getAllCustomer()
            .onSuccess {
                view.randomCustomerSuccess(it[RandomSingleton.getInstance().nextInt(it.size)])
            }
            .onFailure {
                view.randomCustomerFailure()
            }
            .call()
    }

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun saveBill(bill: Bill) {
        dataManager.createBill(bill)
            .onSuccess { view.saveBillSuccess() }
            .onFailure { view.saveBillFailure() }
            .call()
    }
}