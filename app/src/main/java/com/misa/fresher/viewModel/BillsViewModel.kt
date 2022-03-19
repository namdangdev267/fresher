package com.misa.fresher.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.data.DataForTest
import com.misa.fresher.model.Bill
import java.text.SimpleDateFormat
import java.util.*

class BillsViewModel : ViewModel() {
    private var _listBill = MutableLiveData<MutableList<Bill>>(mutableListOf())
    val listBill: LiveData<MutableList<Bill>> get() = _listBill

    private val _filterList = MutableLiveData<MutableList<Bill>>(mutableListOf())
    val filterList: LiveData<MutableList<Bill>> get() = _filterList

    fun initData() {
        _listBill.value = DataForTest.listBills
        _listBill.postValue(_listBill.value)
    }

    fun addBill(bill: Bill) {
        _listBill.value?.add(bill)
        _listBill.postValue(_listBill.value)
    }

    fun getTotalPrice() = _filterList.value?.let { listbill ->
        listbill.sumOf { bill ->
            bill.listSelectedProduct?.let { listitem ->
                listitem.sumOf {
                    it.amonut * it.product.price
                }
            }?.toDouble() ?: 0.0
        }
    }

    fun filterBill(sortBy: String) {
        var dt = Date()
        val c = Calendar.getInstance()
        c.setTime(dt)
        when (sortBy) {
            "Hôm nay" -> {
                c.add(Calendar.DATE, 0)
                dt = c.getTime()
                _filterList.value = _listBill.value?.filter {
                    it.date == SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>?
            }
            "Hôm qua" -> {
                c.add(Calendar.DATE, -1)
                dt = c.getTime()
                _filterList.value = _listBill.value?.filter {
                    it.date == SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>?
            }
            "Hôm kia" -> {
                c.add(Calendar.DATE, -2)
                dt = c.getTime()
                _filterList.value = _listBill.value?.filter {
                    it.date == SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>?
            }
            "Tuần này" -> {
                c.add(Calendar.DATE, -7)
                dt = c.getTime()
                _filterList.value = _listBill.value?.filter {
                    it.date >= SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>?
            }
            "Khác" -> {
                c.add(Calendar.DATE, -7)
                dt = c.getTime()
                _filterList.value = _listBill.value?.filter {
                    it.date < SimpleDateFormat("dd/M/yyyy").format(dt)
                } as MutableList<Bill>?
            }
        }
        _filterList.postValue(_filterList.value)
    }
}