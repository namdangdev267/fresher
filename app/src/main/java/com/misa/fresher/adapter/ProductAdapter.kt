package com.misa.fresher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.models.Product

class ProductAdapter(private val products : List<Product>,val clickItemListener :(product :Product)->Unit ) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contextView = inflater.inflate(R.layout.item_product,parent,false)
        return ViewHolder(contextView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(product: Product){
            itemView.findViewById<ImageView>(R.id.img_imageProduct).setImageResource(product.productImage)
            itemView.findViewById<TextView>(R.id.tv_productName).text = product.productName
            itemView.findViewById<TextView>(R.id.tv_productID).text = product.productCode
            itemView.findViewById<TextView>(R.id.tv_productPrice).text = product.price.toString()
            itemView.setOnClickListener{
                clickItemListener(product)
            }
        }
    }
}