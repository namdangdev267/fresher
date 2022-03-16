package com.misa.fresher.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.model.Bill
import com.misa.fresher.model.Receiver

class ShareViewModel:ViewModel() {
    private var _listBill = MutableLiveData<MutableList<Bill>>()
    val listBill : LiveData<MutableList<Bill>> get() = _listBill

    fun addBill(bill: Bill){
        _listBill.value?.add(bill)
        _listBill.postValue(_listBill.value)
    }
    fun getTotalPrice(): Double {
        var totalPrice = 0.0
        _listBill.value?.let {
            for (i in _listBill.value!!){
                totalPrice += i.listSelectedProduct!!.sumOf { it.amonut*it.product.price }
            }
        }
        return totalPrice
    }

    init {
        _listBill.postValue(mutableListOf())
    }

}