package com.misa.fresher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Products
/**
* tạo ProductsAdapter để recyclerView màn sale sử dụng
* @Auther : NTBao
* @date : 3/16/2022
**/
class ProductsAdapter(
    var mProducts: List<Products>,
    val clickItemListener: (products: Products) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(products: Products) {
            itemView.findViewById<TextView>(R.id.tvProductName).text = products.name
            itemView.findViewById<TextView>(R.id.tvProductId).text = products.id
            itemView.findViewById<TextView>(R.id.tvProductPrice).text = products.price.toString()
            itemView.findViewById<ImageView>(R.id.ivProduct).setImageResource(products.img)
            itemView.setOnClickListener {
                clickItemListener(products)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_list_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mProducts[position])
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }
}