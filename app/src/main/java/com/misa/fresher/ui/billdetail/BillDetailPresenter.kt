package com.misa.fresher.ui.billdetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.misa.fresher.data.dao.bill.BillDao
import com.misa.fresher.data.dao.selectedproduct.SelectedProductDao
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.Bill
import com.misa.fresher.data.model.SelectedProducts
import com.misa.fresher.showToast
import com.misa.fresher.ui.sale.SaleFragment
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class BillDetailPresenter(context: Context) : BillDetailContract.Presenter {
    private var view: BillDetailContract.View? = null
    private var listFromSale = mutableListOf<SelectedProducts>()
    private var billId = 0
    val billDao = BillDao.getInstance((AppDbHelper.getInstance(context)))
    val selectedProductsDao = SelectedProductDao.getInstance((AppDbHelper.getInstance(context)))
    override fun getSelectedProducts(bundle: Bundle) {
        listFromSale = bundle.get(SaleFragment.BUNDLE_SELECTEDITEM) as MutableList<SelectedProducts>
        view?.updateRecyclerViewSelectedProducts(listFromSale)
        getSelectedProductStatic()
    }

    override fun getSelectedProductStatic() {
        val totalPrice = listFromSale.sumOf { it.product.price * it.amonut }
        val totalAmount = listFromSale.sumOf { it.amonut }
        view?.updateSelectedProduct(totalAmount, totalPrice)
    }

    override fun getUpdateSelectedClicked(selectedProducts: SelectedProducts) {
        for (selectedPro in listFromSale) {
            if (selectedPro.product == selectedProducts.product) {
                selectedPro.amonut = selectedProducts.amonut
            }
        }
        view?.updateRecyclerViewSelectedProducts(listFromSale)
        getSelectedProductStatic()
    }

    override fun saveBill(billId:Int) {
        val price = listFromSale.sumOf { it.amonut *it.product.price }
        CoroutineScope(Dispatchers.IO).launch {
            for (selected in listFromSale){
                selectedProductsDao.addSelectedProduct(selected,billId)
            }
            billDao.addBill(
                Bill(
                    billId,
                    listFromSale,
                    null,
                    SimpleDateFormat("dd/M/yyyy").format(Date()),
                    price
                )
            )
            withContext(Dispatchers.Main){
                view?.saveSuccess()
            }
        }
    }

    override fun getBillId(context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            val id = billDao.getLastestBillID() + 1
            withContext(Dispatchers.Main) {
                view?.updateBillId(id)
            }
        }
    }

    override fun attach(view: BillDetailContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

}