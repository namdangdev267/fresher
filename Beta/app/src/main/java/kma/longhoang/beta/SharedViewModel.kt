package kma.longhoang.beta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kma.longhoang.beta.model.OrderModel

class SharedViewModel: ViewModel() {
    private val _listOrder = MutableLiveData<MutableList<OrderModel>>()
    val listOrder: LiveData<MutableList<OrderModel>> = _listOrder
    fun setListOrder(orderList: MutableList<OrderModel>){
        _listOrder.postValue(orderList)
    }
}