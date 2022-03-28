package com.misa.fresher.ui.listbills

import android.content.Context
import com.misa.fresher.base.IBaseContract
import com.misa.fresher.data.model.product.ProductBill

class ListBillContract {
    interface View: IBaseContract.View {
        fun updateFilters(dates: ArrayList<String>, categories: ArrayList<String>)
        fun updateListBillRecView(bills: ArrayList<ProductBill>)
        fun updateBillStat(totalAmount: Int, totalPrice: Double)
    }

    interface Presenter: IBaseContract.Presenter<View> {
        fun getFilterOptions()
        suspend fun getBills(context: Context, txtSearch: String? = null)
    }
}