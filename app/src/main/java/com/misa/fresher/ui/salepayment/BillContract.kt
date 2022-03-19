package com.misa.fresher.ui.salepayment

import android.os.Bundle
import com.misa.fresher.base.BaseContract
import com.misa.fresher.data.model.product.Product

/**
 * - class's purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-18
 */

class BillContract {
    interface View: BaseContract.View {
        fun updateListProductRecViewUI(products: ArrayList<Product>)
        fun productPaid()
        fun updateSelectedProductsStatisticUI(totalPrice: Double, totalAmount: Int)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getSelectedProducts(bundle: Bundle?)
        fun payProducts()
        fun getSelectedProductsStatistic()
    }
}