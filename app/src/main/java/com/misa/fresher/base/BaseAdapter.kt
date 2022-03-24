package com.misa.fresher.base

import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemBill

abstract class BaseRecyclerViewAdapter<T,V : RecyclerView.ViewHolder>: RecyclerView.Adapter<V>() {

    lateinit var listData: MutableList<T>

    open fun updateList(list:MutableList<T>)
    {
        this.listData = list
        notifyDataSetChanged()
    }
}