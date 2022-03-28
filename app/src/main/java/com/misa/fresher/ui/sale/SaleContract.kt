package com.misa.fresher.ui.sale

import android.content.Context
import com.misa.fresher.base.IBaseContract
import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.utils.Enums

/**
 * - Class purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-18
 */

class SaleContract {
    interface View : IBaseContract.View {
        fun updateProductRecViewUI(productModels: ArrayList<ProductModel>)

        fun updateProductSelectedUI(totalAmount: Int, totalPrice: Double)

        fun updateSaleFilterDrawerUI(colors: List<String>, sizes: List<String>, categories: List<String>)

        fun navToBillFragment(productModels: ArrayList<ProductModel>)
    }

    interface Presenter : IBaseContract.Presenter<View> {
        suspend fun getAllProducts(context: Context?)

        suspend fun filterProducts(isCheckQTY: Boolean, category: String, viewMode: Enums.Product, sortBy: String, color: String, size: String, search: String = "")

        suspend fun searchProducts(search: String)

        fun clearSelectedProducts()

        fun checkSelectedProducts()

        fun selectProduct(isSplit: Boolean, productModel: ProductModel)

        fun getSelectedProductStatistic()
    }
}