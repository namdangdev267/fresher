package com.misa.fresher.fragment.bill

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.models.ItemBill
import com.misa.fresher.databinding.ItemBillBinding

class ListBillAdapter(private var listItemBill: MutableList<ItemBill>) :
    RecyclerView.Adapter<ListBillAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemBillBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor", "SetTextI18n", "CutPasteId")
        fun bind(itemBill: ItemBill) {
            binding.tvBillId.text = itemBill.id
//            binding.tvBillPrice.text = itemBill.getPrice().toString()
            binding.tvBillPrice.text = itemBill.billPrice.toString()
            Log.d("tagPrice", itemBill.getPrice().toString())
//            binding.tvBillPrice.text = "itemBill.getPrice().toString()"

            val tvBillInforShip = binding.tvBillInforShip

            if (itemBill.inforShip?.receiver != null && itemBill.inforShip?.tel != null) {
                tvBillInforShip.text =
                    itemBill.inforShip!!.receiver + " - " + itemBill.inforShip!!.tel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemBill[position])
    }

    override fun getItemCount() = listItemBill.size
}
