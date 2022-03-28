package com.misa.fresher.views


import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.BindingAdapter
import com.misa.fresher.base.BaseRecyclerViewAdapter
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.models.ItemProduct

@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: MutableList<ItemBill>?) {
    val adapter = recyclerView.adapter as BaseRecyclerViewAdapter<ItemBill,RecyclerView.ViewHolder>
    adapter.updateList(list ?: mutableListOf())
}

@BindingAdapter("submitListBillDetail")
fun submitListBillDetail(recyclerView: RecyclerView, list: MutableList<ItemBillDetail>?) {
    val adapter = recyclerView.adapter as BaseRecyclerViewAdapter<ItemBillDetail,RecyclerView.ViewHolder>
    adapter.updateList(list ?: mutableListOf())
}


@BindingAdapter("submitListProduct")
fun submitListProduct(recyclerView: RecyclerView, list: MutableList<ItemProduct>?) {
    val adapter = recyclerView.adapter as BaseRecyclerViewAdapter<ItemProduct,RecyclerView.ViewHolder>
    adapter.updateList(list ?: mutableListOf())
}