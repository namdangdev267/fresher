package com.misa.fresher.views.fragments.shippingInfomation.Ship

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.models.ItemRecyclerView
import com.misa.fresher.R

class ShipViewModel(context: Context) : ViewModel() {
    var listItemRecyclerView: MutableList<ItemRecyclerView> = mutableListOf()

    init {
        listItemRecyclerView.add(
            ItemRecyclerView.ItemRadioButton(
                context.getString(R.string.organization),
                context.getString(R.string.personal)
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.receiver),
                context.getString(R.string.touch_to_select),
                R.drawable.ic_arrow_down_24,
                null
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.service_type),
                context.getString(R.string.touch_to_select),
                null,
                null
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemCalculator(
                context.getString(R.string.shipping_cost_paid_to_partner),
                "0,0"
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.tracking_no),
                context.getString(R.string.touch_to_enter),
                null,
                null
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.notes),
                context.getString(R.string.touch_to_enter),
                null,
                null
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.shipping_date),
                "28/2/2022",
                null,
                null
            )
        )


    }

}