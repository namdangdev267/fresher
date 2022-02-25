package com.example.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import kotlinx.android.synthetic.main.items_row.view.*

class RcvAdapter : RecyclerView.Adapter<RcvAdapter.MyViewHolder>() {
    var list = mutableListOf<Product>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_row, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.itemView.apply {
            tvProductId.text=currentItem.id
            tvProductName.text = currentItem.name
            tvProductPrice.text = currentItem.price.toString()
            ivProduct.setImageResource(currentItem.img)
        }
    }

    override fun getItemCount() = list.size
}
