package kma.longhoang.beta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kma.longhoang.beta.model.BillModel
import kma.longhoang.beta.model.CustomerModel
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.model.UserResponse

class SaleViewModel : ViewModel() {
    private val _listOrder = MutableLiveData<MutableList<OrderModel>>()
    val listOrder: LiveData<MutableList<OrderModel>> = _listOrder
    fun setListOrder(orderList: MutableList<OrderModel>) {
        _listOrder.postValue(orderList)
    }

    private val _customer = MutableLiveData<CustomerModel?>()
    val customer: LiveData<CustomerModel?> = _customer
    fun setCustomer(customer: CustomerModel?= null) {
        _customer.postValue(customer)
    }
}