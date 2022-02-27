package com.misa.fresher.ui.sale.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Product
import com.misa.fresher.util.toCurrency

abstract class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.DataViewHolder>() {

    private var products = mutableListOf<Product>()

    fun updateProduct(list: MutableList<Product>) {
        products = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.lyItem.setOnClickListener {
            onItemClick(products[position])
        }

        holder.tvName.text = products[position].name
        holder.tvId.text = products[position].id

        var price = products[position].price.toCurrency()
        products[position].maxPrice?.let {
            price += " ~ ${it.toCurrency()}"
        }
        holder.tvPrice.text = price
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lyItem: ConstraintLayout = itemView.findViewById(R.id.btn_item)
        val ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvId: TextView = itemView.findViewById(R.id.tv_id)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
    }

    abstract fun onItemClick(product: Product)
}