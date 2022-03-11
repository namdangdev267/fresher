package com.misa.fresher.Views.Fragments.Sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemSale
import com.misa.fresher.R

class SaleAdapter(private val listItemSale:MutableList<ItemSale>, val listener: (itemSale:ItemSale)->Unit):RecyclerView.Adapter<SaleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val listener: (itemSale: ItemSale) -> Unit): RecyclerView.ViewHolder(itemView){
        fun bind(itemSale: ItemSale)
        {
            itemView.findViewById<TextView>(R.id.name_item).text = itemSale.name
            itemView.findViewById<TextView>(R.id.id_item).text = itemSale.id
            itemView.findViewById<TextView>(R.id.price_item).text = itemSale.price.toString()
            itemView.findViewById<ImageView>(R.id.image_item).setImageResource(R.drawable.ic_shopping_bag)
            itemView.setOnClickListener {
                listener(itemSale)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sale, parent, false)

        return ViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemSale[position])
    }

    override fun getItemCount() = listItemSale.size
}


