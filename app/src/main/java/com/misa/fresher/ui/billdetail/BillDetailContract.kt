package com.misa.fresher.ui.billdetail

import android.content.Context
import android.os.Bundle
import com.misa.fresher.base.BaseContract
import com.misa.fresher.data.model.Customer
import com.misa.fresher.data.model.SelectedProducts

class BillDetailContract : BaseContract {
    interface View : BaseContract.View {
        fun updateRecyclerViewSelectedProducts(list: MutableList<SelectedProducts>)
        fun updateSelectedProduct(amount: Int, price: Double)
        fun updateReceiver(customer: Customer)
        fun saveSuccess()
        fun updateBillId(billId: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getSelectedProducts(bundle: Bundle)
        fun getSelectedProductStatic()
        fun getUpdateSelectedClicked(selectedProducts: SelectedProducts)
        fun saveBill(billId:Int)
        fun getBillId(context: Context)
    }
}