package com.misa.fresher.Views.Fragments.ShippingInfomation.Package

import android.content.Context
import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.ItemRecyclerView
import com.misa.fresher.R

class PackageViewModel(context: Context) : ViewModel() {
    var listItemRecyclerView: MutableList<ItemRecyclerView> = mutableListOf()

    init {
        listItemRecyclerView.add(
            ItemRecyclerView.ItemTouch(
                context.getString(R.string.weight), "100",
                null, null
            )
        )
        listItemRecyclerView.add(
            ItemRecyclerView.Item3Col(
                context.getString(R.string.size),
                "10",
                "10",
                "10"
            )
        )

    }


}