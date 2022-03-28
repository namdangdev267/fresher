package com.misa.fresher.ui.listbills

import android.content.Context
import android.util.Log
import com.misa.fresher.data.model.product.*
import com.misa.fresher.di.Injector
import com.misa.fresher.utils.toArrayList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListBillPresenter : ListBillContract.Presenter {
    private var bills = arrayListOf<ProductBill>()
    private var search = ""
    private var dates = arrayListOf("Today", "Yesterday", "Before Yesterday", "This week", "Other")
    private var categories = arrayListOf("All", "Got payment", "Debit", "Deleted")

    private var view: ListBillContract.View? = null

    override fun attach(view: ListBillContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun getFilterOptions() {
        view?.updateFilters(dates, categories)
    }

    override suspend fun getBills(context: Context, txtSearch: String?) {
        if(txtSearch == null) {
            val pBillRepo = Injector.getProductBillRepository(context)
            val pItemBillRepo = Injector.getItemBillRepository(context)
            val pItemRepo = Injector.getProductItemRepository(context)
            val pModelRepo = Injector.getProductModelRepository(context)
            val pUnitRepo = Injector.getProductUnitRepository(context)

            bills = pBillRepo.getAll()
            bills.forEach { pBill ->
                pItemBillRepo.getByBillId(pBill.id).forEach { itemBill ->
                    pItemRepo.getById(itemBill.itemId)?.let { pItem ->
                        pModelRepo.getById(pItem.modelId)?.let { pModel ->
                            pUnitRepo.getById(itemBill.unitId)?.let { pUnit ->
                                pModel.items = arrayListOf(pItem)
                                pModel.unit = pUnit
                                pModel.amount = itemBill.amount

                                pBill.productModels.add(pModel)
                            }
                        }
                    }
                }
            }
        }

        search = txtSearch ?: ""
        val displayBills = if (search.isNotBlank()) bills.filter { it.id.toString().contains(search) } else bills

        withContext(Dispatchers.Main) {
            view?.updateBillStat(displayBills.size, displayBills.sumOf { it.price })
            view?.updateListBillRecView(displayBills.toArrayList())
        }
    }
}