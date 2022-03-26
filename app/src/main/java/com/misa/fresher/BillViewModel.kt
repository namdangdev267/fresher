package com.misa.fresher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.model.BillInfor

class BillViewModel : ViewModel() {
    private var listBill = MutableLiveData<MutableList<BillInfor>>(arrayListOf())
    val listItemBill: LiveData<MutableList<BillInfor>> get() = listBill
    fun add(billInfor: BillInfor) {
        listBill.value?.add(billInfor)
        listBill.postValue(listBill.value)
    }

    fun calculateTotalAmount() =
        listBill.value?.sumOf {
           it.total
        }

    fun getSize() = listBill.value?.size
}