package com.misa.fresher.ui.listbill

import com.misa.fresher.common.FakeData
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
    view: ListBillContract.View
) : BasePresenter<ListBillContract.View>(view), ListBillContract.Presenter {

    private val filter: FilterBillModel = FilterBillModel()

    override fun filterByKeyword(keyword: String) {
        filter.keyword = keyword
        val filterItems = filter.filter(FakeData.bills)
        view.updateBillList(filterItems.toMutableList())
    }

    override fun filterByTime(time: TimeFilterType) {
        filter.time = time
        val filterItems = filter.filter(FakeData.bills)
        view.updateBillList(filterItems.toMutableList())
    }
}