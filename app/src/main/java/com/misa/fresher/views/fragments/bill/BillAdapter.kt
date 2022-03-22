package com.misa.fresher.views.fragments.bill

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemBill
import com.misa.fresher.R
import com.misa.fresher.databinding.ItemBillBinding

class BillAdapter(private var listItemBill: MutableList<ItemBill>) :
    RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemBillBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun bind(itemBill: ItemBill) {
            binding.tvBillId.text = itemBill.id
            binding.tvBillPrice.text = itemBill.getPrice().toString()

            if (itemBill.inforShip?.receiver != null && itemBill.inforShip?.tel != null) {
                binding.tvBillInforShip.text = itemBill.inforShip?.receiver + "_" + itemBill.inforShip?.tel
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
        holder.bind(listItemBill[position])
    }

    override fun getItemCount() = listItemBill.size
}
