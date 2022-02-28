package com.misa.fresher.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Product
import com.misa.fresher.R

open class ProductAdapter(var productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val productView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_view, parent, false)
        return MyViewHolder(productView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imgProduct.setImageResource(R.drawable.clothes)
        holder.tvNameProduct.text = productList[position].nameProduct
        holder.tvCodeProduct.text = productList[position].codeProduct
        holder.tvPriceProduct.text = productList[position].priceProduct.toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct = itemView.findViewById<ImageView>(R.id.imgProduct)
        val tvNameProduct = itemView.findViewById<TextView>(R.id.tvNameProduct)
        val tvCodeProduct = itemView.findViewById<TextView>(R.id.tvCodeProduct)
        val tvPriceProduct = itemView.findViewById<TextView>(R.id.tvPriceProduct)
    }
}
