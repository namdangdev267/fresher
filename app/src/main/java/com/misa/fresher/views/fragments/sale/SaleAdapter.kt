package com.misa.fresher.views.fragments.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.base.BaseRecyclerViewAdapter
import com.misa.fresher.models.ItemProduct
import com.misa.fresher.databinding.ItemSaleBinding
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.views.fragments.billDetail.BillDetailAdapter


class SaleAdapter(
    private val saleViewModel: SaleViewModel
) : BaseRecyclerViewAdapter<ItemProduct, SaleAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:ItemSaleBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(itemProduct: ItemProduct) {
            binding.itemProduct =  itemProduct
            binding.saleViewModel = saleViewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSaleBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}


