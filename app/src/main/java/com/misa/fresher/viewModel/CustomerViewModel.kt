package com.misa.fresher.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.model.Customer


class CustomerViewModel: ViewModel() {
    private var _customer = MutableLiveData<Customer?>()
    val customer : LiveData<Customer?> get() = _customer
    fun addCustomer(cus:Customer){
        _customer.value=cus
        _customer.postValue(_customer.value)
    }
    fun deleteCustomer(){
        _customer.value = null
        _customer.postValue(_customer.value)
    }
}