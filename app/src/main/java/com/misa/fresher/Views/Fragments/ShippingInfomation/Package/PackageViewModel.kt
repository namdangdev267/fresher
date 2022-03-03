package com.misa.fresher.Views.Fragments.ShippingInfomation.Package

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.ItemShip
import com.misa.fresher.R

class PackageViewModel(context: Context) : ViewModel() {
    var listItemShip:MutableList<ItemShip> = mutableListOf()

    init {
        listItemShip.add(
            ItemShip.ItemTouch(context.getString(R.string.weight),"100",
                null,null))
        listItemShip.add(ItemShip.Item3Col(context.getString(R.string.size),"10","10","10"))

    }


}