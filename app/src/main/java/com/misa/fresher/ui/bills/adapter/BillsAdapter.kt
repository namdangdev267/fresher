package com.misa.fresher.ui.bills.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.ItemListBillBinding
import com.misa.fresher.data.model.Bill

/**
 * tạo class BillsAdapter để sử dụng cho recyclerview màn Bills
 * @Auther : NTBao
 * @date : 3/16/2022
 **/
class BillsAdapter(var mBills: MutableList<Bill>) :
    RecyclerView.Adapter<BillsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBillBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvBillId.text = mBills[position].id.toString()
        mBills[position].customer?.let {
            holder.tvCustomer.text = it.name + " - " + it.number
        }
        holder.tvTotalPriceBill.text = "${mBills[position].totalPrice}"
    }

    override fun getItemCount() = mBills.size
    inner class ViewHolder(val binding: ItemListBillBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvBillId = binding.tvIdBill
        val tvCustomer = binding.tvReceiver
        val tvTotalPriceBill = binding.tvTotalPriceBill
    }
}