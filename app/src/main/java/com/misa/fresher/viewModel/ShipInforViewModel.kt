package com.misa.fresher.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.model.Receiver

class ShipInforViewModel:ViewModel() {
    private var _receiver = MutableLiveData<Receiver>()
    val receiver : LiveData<Receiver> get() = _receiver
    fun add(inReceiver: Receiver){
        _receiver.value=inReceiver
        _receiver.postValue(_receiver.value)
    }
}