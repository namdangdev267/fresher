package com.misa.fresher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Product

class ProductsAdapter(
    private val mProducts: List<Product>,
    val clickItemListener: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            itemView.findViewById<TextView>(R.id.tvProductName).text = product.name
            itemView.findViewById<TextView>(R.id.tvProductId).text = product.id
            itemView.findViewById<TextView>(R.id.tvProductPrice).text = product.price.toString()
            itemView.findViewById<ImageView>(R.id.ivProduct).setImageResource(product.img)
            itemView.setOnClickListener {
                clickItemListener(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.items_list_product, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mProducts[position])
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }
}