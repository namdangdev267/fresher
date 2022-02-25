package com.misa.fresher.Views.Fragments.Sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.Item
import com.misa.fresher.R

class SaleAdapter(private val listItem:MutableList<Item>):RecyclerView.Adapter<SaleAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tvName = view.findViewById<TextView>(R.id.name_item)
        var tvID= view.findViewById<TextView>(R.id.id_item)
        var tvPrice = view.findViewById<TextView>(R.id.price_item)
        var imvImage = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sale, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imvImage.setImageResource(R.drawable.ic_shopping_bag)
        holder.tvName.text = listItem[position].name
        holder.tvID.text = listItem[position].id
        holder.tvPrice.text = listItem[position].price.toString()
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}


