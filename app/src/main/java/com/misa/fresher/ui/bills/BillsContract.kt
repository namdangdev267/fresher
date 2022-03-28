package com.misa.fresher.ui.bills

import android.content.Context
import com.misa.fresher.base.BaseContract
import com.misa.fresher.data.model.Bill

class BillsContract:BaseContract {
    interface View : BaseContract.View{
        fun upDateReclerView(list: MutableList<Bill>)
        fun getValuesForFilter()
    }
    interface Presenter : BaseContract.Presenter<View>{
        fun getListBillsForAdapter(context: Context)
        fun getFilterBills(date : String)
    }
}