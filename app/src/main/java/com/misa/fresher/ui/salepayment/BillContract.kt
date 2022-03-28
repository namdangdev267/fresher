package com.misa.fresher.ui.salepayment

import android.content.Context
import android.os.Bundle
import com.misa.fresher.base.IBaseContract
import com.misa.fresher.data.model.product.ProductModel

/**
 * - class's purpose:
 *
 * @author HTLong
 * @edit_at 2022-03-18
 */

class BillContract {
    interface View: IBaseContract.View {
        fun updateListProductRecViewUI(productModels: ArrayList<ProductModel>)
        fun productPaid()
        fun updateSelectedProductsStatisticUI(totalPrice: Double, totalAmount: Int)
    }

    interface Presenter: IBaseContract.Presenter<View> {
        fun getSelectedProducts(bundle: Bundle?)
        suspend fun payProducts(context: Context?)
        fun getSelectedProductsStatistic()
    }
}