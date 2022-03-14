package com.misa.fresher.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Product
import com.misa.fresher.R

open class ProductAdapter(
    private val productList: List<Product>,
    val onClickItemListener: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val productView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_view, parent, false)
        return MyViewHolder(productView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initData(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initData(product: Product) {
            itemView.findViewById<ImageView>(R.id.imgProduct).setImageResource(R.drawable.clothes)
            itemView.findViewById<TextView>(R.id.tvNameProduct).text = product.nameProduct
            itemView.findViewById<TextView>(R.id.tvCodeProduct).text = product.codeProduct
            itemView.findViewById<TextView>(R.id.tvPriceProduct).text =
                product.priceProduct.toString()
            itemView.setOnClickListener {
                onClickItemListener(product)
            }
        }
    }
}
