package com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.ItemRecyclerView
import com.misa.fresher.R

class ReceiverViewModel(context: Context) : ViewModel() {
    var listItemRecyclerView: MutableList<ItemRecyclerView> = mutableListOf()

    init {
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.receiver),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_add_24,
                "*"
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.tel),
                context.getString(R.string.touch_to_enter),
                null,
                "*"
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.address),
                context.getString(R.string.touch_to_enter),
                null,
                "*"
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.area),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_arrow_right_24,
                null
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.ward_commune),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_arrow_right_24,
                null
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemCalculator(
                context.getString(R.string.ship_paid_by_cust),
                "0,0"
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemMultiContent(
                context.getString(R.string.deposit_method),
                context.getString(R.string.deposit),
                context.getString(R.string.transfer),
                "0,0",
                R.drawable.ic_arrow_down_24
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.sale_channel),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_arrow_down_24,
                null
            )
        )
        listItemRecyclerView.add(ItemRecyclerView.ItemCheck(context.getString(R.string.collect_COD)))
    }


}