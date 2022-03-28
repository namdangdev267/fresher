package com.misa.fresher.views.fragments.bill

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemBill
import com.misa.fresher.R
import com.misa.fresher.base.BaseRecyclerViewAdapter
import com.misa.fresher.databinding.ItemBillBinding
import com.misa.fresher.getNumString

class BillAdapter : BaseRecyclerViewAdapter<ItemBill,BillAdapter.ViewHolder>()
 {
    class ViewHolder(private val binding: ItemBillBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemBill: ItemBill) {
            binding.itemBill = itemBill
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}
