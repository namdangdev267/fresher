package com.misa.fresher.Fragment.Bill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemBill
import com.misa.fresher.R

class ListBillAdapter(private var listItemBill: MutableList<ItemBill>) :
    RecyclerView.Adapter<ListBillAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemBill: ItemBill) {
            itemView.findViewById<TextView>(R.id.tv_bill_id).text = itemBill.id
            itemView.findViewById<TextView>(R.id.tv_bill_price).text =
                itemBill.getPrice().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bill, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listItemBill[position])
    }

    override fun getItemCount() = listItemBill.size
}
