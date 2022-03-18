package com.misa.fresher.ui.billdetails

import android.os.Bundle
import com.misa.fresher.data.model.product.Product
import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.global.FakeData
import com.misa.fresher.ui.sale.SaleFragment

class BillPresenter: BillContract.Presenter {
    private var view: BillContract.View? = null
    private var selectedItems = arrayListOf<Product>()


    override fun attach(view: BillContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getSelectedProducts(bundle: Bundle?) {
        selectedItems =bundle?.get(SaleFragment.BUNDLE_SELECTED_ITEMS)
                as? ArrayList<Product> ?: arrayListOf()
        view?.updateListProductRecViewUI(selectedItems)
    }



    override fun getSelectedProductsStatistic() {
        val totalPrice = selectedItems.sumOf { it.price }
        val totalAmount = selectedItems.sumOf { it.amount }
        view?.updateSelectedProductsStatisticUI(totalPrice, totalAmount)
    }

    override fun payProducts() {
        val products = ArrayList<Product>(selectedItems)
        selectedItems.clear()
        val bill = ProductBill(
            date = System.currentTimeMillis(),
            customer = "test customer",
            products =  products
        )
        FakeData.productBills.add(bill)

        view?.productPaid()
    }
}