package com.misa.fresher.fragment.shipinformation.ship

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.data.models.ItemShipInfor
import com.misa.fresher.R

class ShipViewModel(context: Context): ViewModel()  {
    var listItemShip:MutableList<ItemShipInfor> = mutableListOf()

    init {
        listItemShip.add(
            ItemShipInfor.ItemRadioButton(context.getString(R.string.organization),context.getString(
            R.string.personal)))
        listItemShip.add(
            ItemShipInfor.ItemTouch(context.getString(R.string.receiver),context.getString(R.string.touch_to_select),
            R.drawable.ic_baseline_keyboard_arrow_down_24,null))
        listItemShip.add(
            ItemShipInfor.ItemTouch(context.getString(R.string.service_type),context.getString(
            R.string.touch_to_select),null,null))
        listItemShip.add(ItemShipInfor.ItemCalculator(context.getString(R.string.shipping_cost_paid_to_partner),"0,0"))
        listItemShip.add(
            ItemShipInfor.ItemTouch(context.getString(R.string.tracking_no),context.getString(
            R.string.touch_to_enter),null,null))
        listItemShip.add(ItemShipInfor.ItemTouch(context.getString(R.string.notes),context.getString(R.string.touch_to_enter),null,null))
        listItemShip.add(ItemShipInfor.ItemTouch(context.getString(R.string.shipping_date),"28/2/2022",null,null))
    }

}