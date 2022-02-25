package com.misa.fresher.Views.Fragments.Sale

import androidx.lifecycle.ViewModel
import com.misa.fresher.Models.Item


class SaleViewModel: ViewModel() {
    lateinit var listItem:MutableList<Item>

    init {
        listItem = mutableListOf()
        for(i in 1..20)
        {
            listItem.add(Item(i.toString()+"abcde",(i*1.01).toFloat(),i.toString()+"AA"))
        }
    }

    @JvmName("getListItem1")
    fun getListItem():MutableList<Item>
    {
        return listItem
    }
}