package com.misa.fresher.Views.Fragments.BillDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemBillDetail
import com.misa.fresher.R

class BillDetailAdapter(private var listItemBillDetail:MutableList<ItemBillDetail>, val listener:(itemBillDetail:ItemBillDetail)->Unit ):
    RecyclerView.Adapter<BillDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val listener: (itemBillDetail: ItemBillDetail) -> Unit): RecyclerView.ViewHolder(itemView){
        fun bind(itemBillDetail: ItemBillDetail)
        {
            itemView.findViewById<TextView>(R.id.tv_item_total_price).text = ((itemBillDetail.itemProduct.price*itemBillDetail.quantity).toString())
            itemView.findViewById<TextView>(R.id.tv_item_quantity).text = itemBillDetail.quantity.toString()
            itemView.findViewById<TextView>(R.id.name_item).text = itemBillDetail.name
            itemView.findViewById<TextView>(R.id.id_item).text = itemBillDetail.id
            itemView.findViewById<TextView>(R.id.price_item).text = itemBillDetail.itemProduct.price.toString()+"/Package"
            itemView.findViewById<ImageView>(R.id.image_item).visibility = View.GONE

            itemView.findViewById<ImageView>(R.id.iv_item_add).setOnClickListener {
                itemBillDetail.quantity+=1
                listener(itemBillDetail)

            }

            itemView.findViewById<ImageView>(R.id.iv_item_remove).setOnClickListener {
                if(itemBillDetail.quantity>1)
                {
                    itemBillDetail.quantity-=1
                }
                listener(itemBillDetail)

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sale_and_bill_detail, parent, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemBillDetail[position])
    }

    override fun getItemCount() = listItemBillDetail.size
}


