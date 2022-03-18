package com.misa.fresher.views.fragments.bill

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemBill
import com.misa.fresher.R

class BillAdapter(private var listItemBill: MutableList<ItemBill>) :
    RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceAsColor")
        fun bind(itemBill: ItemBill) {
            itemView.findViewById<TextView>(R.id.tv_bill_id).text = itemBill.id
            itemView.findViewById<TextView>(R.id.tv_bill_price).text =
                itemBill.getPrice().toString()


            if(itemBill.inforShip?.receiver!=null && itemBill.inforShip?.tel!=null)
            {
                itemView.findViewById<TextView>(R.id.tv_bill_infor_ship).text= itemBill.inforShip?.receiver +"-"+itemBill.inforShip?.tel
            }
            else
            {
                itemView.findViewById<TextView>(R.id.tv_bill_infor_ship).setTextColor(R.color.black)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bill, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemBill[position])
    }

    override fun getItemCount() = listItemBill.size
}
