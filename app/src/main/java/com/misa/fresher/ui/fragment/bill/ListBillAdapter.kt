package com.misa.fresher.fragment.bill

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.models.ItemBill
import com.misa.fresher.databinding.ItemBillBinding

class ListBillAdapter :
    ListAdapter<ItemBill, ListBillAdapter.ListBillViewHolder>(PostDiffCallBack()) {

    class ListBillViewHolder(private val binding: ItemBillBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor", "SetTextI18n", "CutPasteId")
        fun bindingData(itemBill: ItemBill) {
            binding.tvBillId.text = itemBill.id
            binding.tvBillPrice.text = itemBill.billPrice.toString()
            Log.d("tagPrice", itemBill.getPrice().toString())
            val tvBillInforShip = binding.tvBillInforShip
            if (itemBill.inforShip?.receiver != null && itemBill.inforShip?.tel != null) {
                tvBillInforShip.text =
                    itemBill.inforShip!!.receiver + " - " + itemBill.inforShip!!.tel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBillViewHolder =
        ListBillViewHolder(
            ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ListBillViewHolder, position: Int) =
        holder.bindingData(getItem(position))

}

class PostDiffCallBack : DiffUtil.ItemCallback<ItemBill>() {
    override fun areItemsTheSame(oldItem: ItemBill, newItem: ItemBill): Boolean =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ItemBill, newItem: ItemBill): Boolean =
        oldItem == newItem
}


