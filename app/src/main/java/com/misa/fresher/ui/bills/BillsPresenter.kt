package com.misa.fresher.ui.bills

import android.content.Context
import com.misa.fresher.data.dao.bill.BillDao
import com.misa.fresher.data.database.AppDbHelper
import com.misa.fresher.data.model.Bill
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class BillsPresenter(context: Context) : BillsContract.Presenter {
    private var view: BillsContract.View? = null
    private var listBill = mutableListOf<Bill>()
    val billDao = BillDao.getInstance((AppDbHelper.getInstance(context)))
    override fun getListBillsForAdapter(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            listBill = billDao.getAllBill()
            withContext(Dispatchers.Main) {
                view?.getValuesForFilter()
            }
        }
    }

    override fun getFilterBills(sortBy: String) {
        var bills = mutableListOf<Bill>()
        var dt = Date()
        val c = Calendar.getInstance()
        c.setTime(dt)
        when (sortBy) {
            "Hôm nay" -> {
                c.add(Calendar.DATE, 0)
                dt = c.getTime()
                bills = listBill.filter {
                    it.date == SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>
            }
            "Hôm qua" -> {
                c.add(Calendar.DATE, -1)
                dt = c.getTime()
                bills = listBill.filter {
                    it.date == SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>
            }
            "Hôm kia" -> {
                c.add(Calendar.DATE, -2)
                dt = c.getTime()
                bills = listBill.filter {
                    it.date == SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>
            }
            "Tuần này" -> {
                c.add(Calendar.DATE, -7)
                dt = c.getTime()
                bills = listBill.filter {
                    it.date >= SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>
            }
            "Khác" -> {
                c.add(Calendar.DATE, -7)
                dt = c.getTime()
                bills = listBill.filter {
                    it.date < SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>
            }
        }
        view?.upDateReclerView(bills)
    }

    override fun attach(view: BillsContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

}

