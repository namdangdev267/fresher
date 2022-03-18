package com.misa.fresher.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.model.Bill
import com.misa.fresher.model.Receiver

class BillsViewModel:ViewModel() {
    private var _listBill = MutableLiveData<MutableList<Bill>>(mutableListOf())
    val listBill : LiveData<MutableList<Bill>> get() = _listBill

    fun addBill(bill: Bill){
        _listBill.value?.add(bill)
        _listBill.postValue(_listBill.value)
    }
    fun getTotalPrice() = _listBill.value?.let { listbill ->
        listbill.sumOf { bill ->
            bill.listSelectedProduct?.let { listitem ->
                listitem.sumOf {
                    it.amonut * it.product.price
                }
            }?.toDouble() ?: 0.0
        }
    }

}