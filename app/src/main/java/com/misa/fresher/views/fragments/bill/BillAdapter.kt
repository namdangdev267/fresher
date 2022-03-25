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
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun bind(itemBill: ItemBill) {
            binding.tvBillId.text =  itemBill.id.getNumString().substring(10)
            binding.tvBillPrice.text = itemBill.billPrice.toString()

            if (itemBill.infoShip?.receiver != null && itemBill.infoShip?.tel != null) {
                binding.tvBillInforShip.text = itemBill.infoShip?.receiver + "-" + itemBill.infoShip?.tel
            } else {
                binding.tvBillInforShip.setTextColor(R.color.black)
            }
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
