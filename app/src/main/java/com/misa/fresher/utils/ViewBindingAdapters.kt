package com.misa.fresher.views

import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.views.fragments.bill.BillAdapter
import androidx.databinding.BindingAdapter
import com.misa.fresher.base.BaseRecyclerViewAdapter
import com.misa.fresher.models.ItemBill

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseRecyclerViewAdapter<ItemBill,RecyclerView.ViewHolder>
) {
    recyclerView.adapter = adapter
}

@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: MutableList<ItemBill>?) {
    val adapter = recyclerView.adapter as BaseRecyclerViewAdapter<ItemBill,RecyclerView.ViewHolder>
    adapter.updateList(list ?: mutableListOf())
}