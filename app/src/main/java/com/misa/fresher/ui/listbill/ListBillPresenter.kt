package com.misa.fresher.ui.listbill

import android.content.Context
import com.misa.fresher.core.BasePresenter
import com.misa.fresher.data.model.FilterBillModel
import com.misa.fresher.util.enum.TimeFilterType

/**
 * Presenter cho màn hình danh sách hóa đơn
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 1
 * @updated 3/21/2022: Tạo class
 */
class ListBillPresenter(
    view: ListBillContract.View,
    context: Context
) : BasePresenter<ListBillContract.View>(view, context), ListBillContract.Presenter {

    private val filter: FilterBillModel = FilterBillModel()

    /**
     * @version 2
     * @updated 3/23/2022: Override lần đầu
     * @updated 3/25/2022: Chuyển sang trao đổi dữ liệu với database
     */
    override fun filterByKeyword(keyword: String) {
        filter.keyword = keyword
        dataManager.getAllBill()
            .onSuccess {
                val filterItems = filter.filter(it)
                view.updateBillList(filterItems.toMutableList())
            }
            .call()
    }

    /**
     * @version 2
     * @updated 3/23/2022: Override lần đầu
     * @updated 3/25/2022: Chuyển sang trao đổi dữ liệu với database
     */
    override fun filterByTime(time: TimeFilterType) {
        filter.time = time
        dataManager.getAllBill()
            .onSuccess {
                val filterItems = filter.filter(it)
                view.updateBillList(filterItems.toMutableList())
            }
            .call()
    }
}