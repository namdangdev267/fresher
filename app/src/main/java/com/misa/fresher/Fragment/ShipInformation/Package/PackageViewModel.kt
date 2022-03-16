package com.misa.fresher.Fragment.ShipInformation.Package

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.ItemShipInfor
import com.misa.fresher.R

class PackageViewModel(context: Context) : ViewModel() {
    var listItemShip:MutableList<ItemShipInfor> = mutableListOf()

    init {
        listItemShip.add(
            ItemShipInfor.ItemTouch(context.getString(R.string.weight),"100",
                null,null))
        listItemShip.add(ItemShipInfor.Item3Col(context.getString(R.string.size),"10","10","10"))

    }

}