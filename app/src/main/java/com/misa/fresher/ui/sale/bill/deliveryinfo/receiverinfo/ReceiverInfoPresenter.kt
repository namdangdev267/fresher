package com.misa.fresher.ui.sale.bill.deliveryinfo.receiverinfo

import android.content.Context
import com.misa.fresher.common.RandomSingleton
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
    view: ReceiverInfoContract.View,
    context: Context
) : BasePresenter<ReceiverInfoContract.View>(view, context), ReceiverInfoContract.Presenter {

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
}