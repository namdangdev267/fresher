package com.misa.fresher.ui.sale

import com.misa.fresher.base.BaseContract
import com.misa.fresher.data.model.product.Product
import com.misa.fresher.utils.Enums

/**
 * - Class purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-18
 */

class SaleContract {
    interface View : BaseContract.View {
        fun updateProductRecViewUI(products: ArrayList<Product>)

        fun updateProductSelectedUI(totalAmount: Int, totalPrice: Double)

        fun updateSaleFilterDrawerUI(colors: List<String>, sizes: List<String>, categories: List<String>)

        fun navToBillFragment(products: ArrayList<Product>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getDisplayProducts()

        fun clearSelectedProducts()

        fun checkSelectedProducts()

        fun selectProduct(isSplit: Boolean, product: Product)

        fun getFilterOptions()

        fun filterProducts(isCheckQTY: Boolean, category: String, viewMode: Enums.Product, sortBy: String, color: String, size: String, search: String = "")

        fun searchProducts(search: String)

        fun getSelectedProductStatistic()
    }
}