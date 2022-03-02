package com.misa.fresher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Product

class ProductsAdapter(private val mProducts:List<Product>):RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val productName = itemView.findViewById<TextView>(R.id.tvProductName)
        val productId = itemView.findViewById<TextView>(R.id.tvProductId)
        val productPrice = itemView.findViewById<TextView>(R.id.tvProductPrice)
        val productImg = itemView.findViewById<ImageView>(R.id.ivProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.items_row,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: Product = mProducts.get(position)
        holder.productId.text = product.id
        holder.productName.text = product.name
        holder.productPrice.text = product.price.toString()
        holder.productImg.setImageResource(product.img)
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }
}