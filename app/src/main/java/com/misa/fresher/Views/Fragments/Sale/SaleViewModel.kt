package com.misa.fresher.Views.Fragments.Sale

import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.ItemSale


class SaleViewModel: ViewModel() {
    var listItemSale:MutableList<ItemSale> = mutableListOf()

    init {
        for(i in 1..20)
        {
            listItemSale.add(ItemSale(i.toString()+"abcde",(i*1.01).toFloat(),i.toString()+"AA"))
        }
    }
}