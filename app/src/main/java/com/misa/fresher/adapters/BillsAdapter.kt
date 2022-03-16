package com.misa.fresher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Bill

class BillsAdapter(private val mBills: MutableList<Bill>) :
    RecyclerView.Adapter<BillsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.item_list_bill, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: BillsAdapter.ViewHolder, position: Int) {
        holder.bind(mBills[position])
    }

    override fun getItemCount() = mBills.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Bill) {
            itemView.findViewById<TextView>(R.id.tvIdBill).text = item.id.toString()
            itemView.findViewById<TextView>(R.id.tvReceiver).text=item.receiver?.name
            itemView.findViewById<TextView>(R.id.tvTotalPriceBill).text =
                "${item.listSelectedProduct?.sumOf { it.amonut * it.product.price }}"
        }
    }
}