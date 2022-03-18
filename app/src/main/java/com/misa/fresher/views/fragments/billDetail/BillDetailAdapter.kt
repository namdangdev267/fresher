package com.misa.fresher.views.fragments.billDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.R
import com.misa.fresher.showToastUp
import com.misa.fresher.showToastUp
import com.misa.fresher.views.customViews.CustomToast

class BillDetailAdapter(
    private var listItemBillDetail: MutableList<ItemBillDetail>,
    val listener: (itemBillDetail: ItemBillDetail) -> Unit
) :
    RecyclerView.Adapter<BillDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val listener: (itemBillDetail: ItemBillDetail) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(itemBillDetail: ItemBillDetail) {
            itemView.findViewById<TextView>(R.id.tv_item_total_price).text =
                (itemBillDetail.getAllPrice().toString())
            itemView.findViewById<TextView>(R.id.tv_item_quantity).text =
                itemBillDetail.quantity.toString()
            itemView.findViewById<TextView>(R.id.name_item).text = itemBillDetail.name
            itemView.findViewById<TextView>(R.id.id_item).text = itemBillDetail.id
            itemView.findViewById<TextView>(R.id.price_item).text =
                itemBillDetail.itemProduct.price.toString() + "/Package"
            itemView.findViewById<ImageView>(R.id.image_item).visibility = View.GONE

            itemView.findViewById<ImageView>(R.id.iv_item_add).setOnClickListener {
                itemBillDetail.quantity += 1
                listener(itemBillDetail)

            }

            itemView.findViewById<ImageView>(R.id.iv_item_remove).setOnClickListener {
                if (itemBillDetail.quantity > 1) {
                    itemBillDetail.quantity -= 1
                    listener(itemBillDetail)
                }
                else if(itemBillDetail.quantity==1)
                {
//                    itemView.context.showToastUp("Quantity must be more than 0. Please check again")
                    CustomToast.makeText(itemView.context,"Quantity must be more than 0. Please check again",Toast.LENGTH_SHORT)
                }


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


