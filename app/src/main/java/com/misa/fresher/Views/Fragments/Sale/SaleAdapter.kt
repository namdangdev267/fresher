package com.misa.fresher.Views.Fragments.Sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemProduct
import com.misa.fresher.R

class SaleAdapter(
    private var listItemProduct: MutableList<ItemProduct>,
    val listener: (itemProduct: ItemProduct) -> Unit
) : RecyclerView.Adapter<SaleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val listener: (itemProduct: ItemProduct) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(itemProduct: ItemProduct) {
            itemView.findViewById<ImageView>(R.id.iv_item_add).visibility = View.GONE
            itemView.findViewById<ImageView>(R.id.iv_item_remove).visibility = View.GONE
            itemView.findViewById<TextView>(R.id.tv_item_quantity).visibility = View.GONE
            itemView.findViewById<TextView>(R.id.tv_item_total_price).visibility = View.GONE

            itemView.findViewById<TextView>(R.id.name_item).text = itemProduct.name
            itemView.findViewById<TextView>(R.id.id_item).text = itemProduct.id
            itemView.findViewById<TextView>(R.id.price_item).text = itemProduct.price.toString()
            itemView.findViewById<ImageView>(R.id.image_item)
                .setImageResource(R.drawable.ic_shopping_bag)
            itemView.setOnClickListener {
                listener(itemProduct)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sale_and_bill_detail, parent, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemProduct[position])
    }

    override fun getItemCount() = listItemProduct.size
}


