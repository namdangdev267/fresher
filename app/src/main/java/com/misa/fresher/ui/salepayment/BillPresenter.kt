package com.misa.fresher.ui.salepayment

import android.content.Context
import android.os.Bundle
import com.misa.fresher.data.model.product.ItemBill
import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.di.Injector
import com.misa.fresher.ui.sale.SaleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillPresenter : BillContract.Presenter {
    private var view: BillContract.View? = null
    private var selectedItems = arrayListOf<ProductModel>()


    override fun attach(view: BillContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getSelectedProducts(bundle: Bundle?) {
        selectedItems = bundle?.get(SaleFragment.BUNDLE_SELECTED_ITEMS) as? ArrayList<ProductModel> ?: arrayListOf()
        view?.updateListProductRecViewUI(selectedItems)
    }

    override fun getSelectedProductsStatistic() {
        val totalPrice = selectedItems.sumOf { it.price }
        val totalAmount = selectedItems.sumOf { it.amount }
        view?.updateSelectedProductsStatisticUI(totalPrice, totalAmount)
    }

    override suspend fun payProducts(context: Context?) {
        context?.let {
            val products = ArrayList<ProductModel>(selectedItems)
            selectedItems.clear()

            val pBillRepo = Injector.getProductBillRepository(it)
            val itemBill = Injector.getItemBillRepository(it)

            val billId = pBillRepo.insert(ProductBill(date = System.currentTimeMillis(), customer = "customer"))

            products.forEach { pModel ->
                if (pModel.items.isNotEmpty()) itemBill.insert(
                    ItemBill(itemId = pModel.items[0].id, billId = billId.toInt(), unitId = pModel.unit.id, amount = pModel.amount)
                )
            }

            withContext(Dispatchers.Main) {
                view?.productPaid()
            }
        }
    }
}