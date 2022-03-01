package com.misa.fresher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R

class ProductApdapter(private val mProducts: List<Product>) :
    RecyclerView.Adapter<ProductApdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val product_image = itemView.findViewById<ImageView>(R.id.product_Image)
        val product_name = itemView.findViewById<TextView>(R.id.product_name)
        val product_abb_name = itemView.findViewById<TextView>(R.id.product_abb_name)
        val product_price = itemView.findViewById<TextView>(R.id.product_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val li = LayoutInflater.from(context)
        val contactView = li.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.product_image.setImageResource(R.drawable.giay)
        holder.product_name.text = mProducts[position].product_name
        holder.product_abb_name.text = mProducts[position].product_abb_name
        holder.product_price.text = mProducts[position].product_price.toString()
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }
}