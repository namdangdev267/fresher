package com.misa.fresher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.misa.fresher.model.ShipInfor

class ShipInforModel :ViewModel(){
        private var shipInfor=MutableLiveData<ShipInfor>()
        val shipItem:LiveData<ShipInfor> get() = shipInfor
        fun add(ship: ShipInfor)
        {
                shipInfor.value=ship
                shipInfor.postValue(shipInfor.value)
        }
}