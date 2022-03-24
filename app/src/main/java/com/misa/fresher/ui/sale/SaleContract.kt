package com.misa.fresher.ui.sale

import android.content.Context
import com.misa.fresher.base.BaseContract
import com.misa.fresher.data.model.Customer
import com.misa.fresher.data.model.FilterProducts
import com.misa.fresher.data.model.Products
import com.misa.fresher.data.model.SelectedProducts

class SaleContract : BaseContract{
    interface View : BaseContract.View{
        fun updateRecyclerViewProduct(list : MutableList<Products>)
        fun updateSelectedProduct(amount: Int,price:Double)
        fun updateReceiver(customer: Customer)
        fun getFilter() : FilterProducts
        fun navigation(list : MutableList<SelectedProducts>)


    }
    interface Presenter : BaseContract.Presenter<View>{
        fun getListProductFromDb(context: Context)
        fun selectProduct(products: Products, amount:Int)
        fun getSelectedProduct()
        fun clearSelected()
        fun searchEvent(text:String)
        fun setCustomer()
        fun getListSelectedProductToBillDetail()
        fun filterItems(filter : FilterProducts)
    }
}