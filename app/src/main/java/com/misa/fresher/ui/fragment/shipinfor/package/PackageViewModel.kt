package com.misa.fresher.ui.fragment.shipinfor.`package`

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.R
import com.misa.fresher.data.models.ItemShipInfor

class PackageViewModel(context: Context) : ViewModel() {
    var listItemShip:MutableList<ItemShipInfor> = mutableListOf()

    init {
        listItemShip.add(
            ItemShipInfor.ItemTouch(context.getString(R.string.weight),"100",
                null,null))
        listItemShip.add(ItemShipInfor.Item3Col(context.getString(R.string.size),"10","10","10"))

    }

}