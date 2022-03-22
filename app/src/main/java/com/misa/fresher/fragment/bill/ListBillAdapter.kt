package com.misa.fresher.views.fragments.bill

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemBill
import com.misa.fresher.R

class ListBillAdapter(private var listItemBill: MutableList<ItemBill>) :
    RecyclerView.Adapter<ListBillAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceAsColor", "SetTextI18n", "CutPasteId")
        fun bind(itemBill: ItemBill) {
            itemView.findViewById<TextView>(R.id.tv_bill_id).text = itemBill.id
            itemView.findViewById<TextView>(R.id.tv_bill_price).text =
                itemBill.getPrice().toString()

            val tvBillInforShip = itemView.findViewById<TextView>(R.id.tv_bill_infor_ship)

            if (itemBill.inforShip?.receiver != null && itemBill.inforShip?.tel != null) {
                tvBillInforShip.text =
                    itemBill.inforShip?.receiver + "_" + itemBill.inforShip?.tel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bill, parent, false)

        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemBill[position])
    }

    override fun getItemCount() = listItemBill.size
}
