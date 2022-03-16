package com.misa.fresher.Fragment.ShipInformation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.ItemShipInfor
import com.misa.fresher.R

class ShipInforViewModel(context: Context): ViewModel() {
    var listItemShip:MutableList<ItemShipInfor> = mutableListOf()

    init {
        listItemShip.add(
            ItemShipInfor.ItemTouch(
                context.getString(R.string.receiver),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_baseline_add_24,
                "*"
            )
        )
        listItemShip.add(
            ItemShipInfor.ItemTouch(
                context.getString(R.string.tel),
                context.getString(R.string.touch_to_enter),
                null,
                "*"
            )
        )
        listItemShip.add(
            ItemShipInfor.ItemTouch(
                context.getString(R.string.address),
                context.getString(R.string.touch_to_enter),
                null,
                "*"
            )
        )
        listItemShip.add(
            ItemShipInfor.ItemTouch(
                context.getString(R.string.area),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_baseline_keyboard_arrow_right_24,
                null
            )
        )
        listItemShip.add(
            ItemShipInfor.ItemTouch(
                context.getString(R.string.ward_commune),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_baseline_keyboard_arrow_right_24,
                null
            )
        )
        listItemShip.add(
            ItemShipInfor.ItemCalculator(
                context.getString(R.string.ship_paid_by_cust),
                "0,0"
            )
        )
        listItemShip.add(
            ItemShipInfor.ItemMultiContent(
                context.getString(R.string.deposit_method),
                context.getString(R.string.deposit),
                context.getString(R.string.transfer),
                "0,0",
                R.drawable.ic_baseline_keyboard_arrow_down_24
            )
        )
        listItemShip.add(
            ItemShipInfor.ItemTouch(
                context.getString(R.string.sale_channel),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_baseline_keyboard_arrow_down_24,
                null
            )
        )
        listItemShip.add(ItemShipInfor.ItemCheck(context.getString(R.string.collect_COD)))
    }
}