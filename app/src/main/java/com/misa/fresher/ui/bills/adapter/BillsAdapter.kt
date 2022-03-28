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
        val bill = mBills[position]
        holder.tvBillId.text = bill.id.toString()
        if (bill.customer != null && bill.customer?.name != null && bill.customer?.name != null) {
            holder.tvCustomer.text = bill.customer?.name + " - " + bill.customer?.number
        } else holder.tvCustomer.text = ""
        holder.tvTotalPriceBill.text = "${bill.totalPrice}"
    }

    override fun getItemCount() = mBills.size
    inner class ViewHolder(val binding: ItemListBillBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvBillId = binding.tvIdBill
        val tvCustomer = binding.tvReceiver
        val tvTotalPriceBill = binding.tvTotalPriceBill
    }
}