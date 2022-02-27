package com.misa.fresher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.Product

class ProductViewAdapter: RecyclerView.Adapter<ProductViewAdapter.ViewHolder>() {
    var products: ArrayList<Product> = ArrayList();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productCode.text = products[position].code
        holder.productName.text = products[position].name
        holder.productPrice.text = products[position].price.toString()
    }

    override fun getItemCount(): Int {
        return this.products.size;
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productCode: TextView = itemView.findViewById(R.id.product_code);
        var productName: TextView = itemView.findViewById(R.id.product_name);
        var productPrice: TextView = itemView.findViewById(R.id.product_price);
    }
}