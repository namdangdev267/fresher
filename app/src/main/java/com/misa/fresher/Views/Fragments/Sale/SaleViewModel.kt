package com.misa.fresher.Views.Fragments.Sale

import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.Item


class SaleViewModel: ViewModel() {
    var listItem:MutableList<Item> = mutableListOf()

    init {
        for(i in 1..20)
        {
            listItem.add(Item(i.toString()+"abcde",(i*1.01).toFloat(),i.toString()+"AA"))
        }
    }
}