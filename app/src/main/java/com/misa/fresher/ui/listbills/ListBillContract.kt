package com.misa.fresher.ui.listbills

import com.misa.fresher.base.BaseContract
import com.misa.fresher.data.model.product.ProductBill

class ListBillContract {
    interface View: BaseContract.View {
        fun updateFilters(dates: ArrayList<String>, categories: ArrayList<String>)
        fun updateListBillRecView(bills: ArrayList<ProductBill>)
        fun updateBillStat(totalAmount: Int, totalPrice: Double)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getFilterOptions()
        fun getBills(txtSearch: String = "")
    }
}