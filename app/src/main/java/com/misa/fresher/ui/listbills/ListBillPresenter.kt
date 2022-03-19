package com.misa.fresher.ui.listbills

import com.misa.fresher.global.FakeData

class ListBillPresenter : ListBillContract.Presenter {
    private var bills = FakeData.productBills
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

    override fun getBills() {
        view?.updateBillStat(bills.size, bills.sumOf { it.price })
        view?.updateListBillRecView(bills)
    }
}