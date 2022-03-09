package com.example.freshermobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freshermobile.model.ProductModel

class ProductAdapter(private val listProduct: MutableList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imgProduct: ImageView = itemView.findViewById(R.id.img_product)
        var tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        var tvProductAmount: TextView = itemView.findViewById(R.id.tv_product_amount)
        var tvProductPrice: TextView = itemView.findViewById(R.id.tv_product_price)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.imgProduct.setImageResource(R.drawable.sample_user_icon)
        holder.tvProductName.text = listProduct[position].name
        holder.tvProductAmount.text = listProduct[position].amount.toString()
        holder.tvProductPrice.text = listProduct[position].price.toString()
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }
}