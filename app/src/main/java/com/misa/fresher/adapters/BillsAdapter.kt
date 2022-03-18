package com.misa.fresher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Bill
/**
* tạo class BillsAdapter để sử dụng cho recyclerview màn Bills
* @Auther : NTBao
* @date : 3/16/2022
**/
class BillsAdapter(var mBills: MutableList<Bill>) :
    RecyclerView.Adapter<BillsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillsAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_bill, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BillsAdapter.ViewHolder, position: Int) {
        holder.bind(mBills[position])
    }

    override fun getItemCount() = mBills.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Bill) {
            itemView.findViewById<TextView>(R.id.tvIdBill).text = item.id.toString()
            if(item.customer!=null){
                itemView.findViewById<TextView>(R.id.tvReceiver).text =
                    item.customer?.name + " - " + item.customer?.number
            }
            itemView.findViewById<TextView>(R.id.tvTotalPriceBill).text =
                "${item.listSelectedProduct?.sumOf { it.amonut * it.product.price }}"
        }
    }
}