package com.misa.fresher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.Product


class ProductViewAdapter(
    private val products: ArrayList<Product>,
    private val itemListener: ItemClickListener
) : RecyclerView.Adapter<ProductViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = products[position]

        holder.productCode.text = item.code
        holder.productName.text = item.name
        holder.productPrice.text = item.price.toString()
        holder.productItem.setOnClickListener {
            itemListener.onClick(item)
        }
        holder.productItem.setOnLongClickListener {
            itemListener.onLongClick(item)
        }
    }

    override fun getItemCount(): Int = this.products.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productItem: RelativeLayout = itemView.findViewById(R.id.product_item)
        var productCode: TextView = itemView.findViewById(R.id.product_code)
        var productName: TextView = itemView.findViewById(R.id.product_name)
        var productPrice: TextView = itemView.findViewById(R.id.product_price)
    }

    interface ItemClickListener {
        fun onClick(item: Product)
        fun onLongClick(item: Product) : Boolean
    }
}


