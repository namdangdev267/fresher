package com.misa.fresher.Views.Fragments.ShippingInfomation.Ship

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.ItemShip
import com.misa.fresher.R

class ShipViewModel(context: Context): ViewModel()  {
    var listItemShip:MutableList<ItemShip> = mutableListOf()

    init {
        listItemShip.add(ItemShip.ItemTouch(context.getString(R.string.receiver),context.getString(R.string.touch_to_select),R.drawable.ic_add_24,"*"))
    }

}