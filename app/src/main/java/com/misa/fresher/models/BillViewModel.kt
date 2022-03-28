package com.misa.fresher.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BillViewModel : ViewModel() {
    private var listBill = MutableLiveData<MutableList<Bill>>(arrayListOf())
    val listItemBill : LiveData<MutableList<Bill>> get() = listBill
    fun add(bill: Bill){
        listBill.value?.add(bill)
        listBill.postValue(listBill.value)
    }

    fun  calculateTotalAmount() = listBill.value?.sumOf {
        it.total
    }

    fun getSize() = listBill.value?.size
}