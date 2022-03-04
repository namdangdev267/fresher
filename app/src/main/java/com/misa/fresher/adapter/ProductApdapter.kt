package com.misa.fresher.adapter

import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Product

class ProductApdapter(private val mProducts: List<Product>) :
    RecyclerView.Adapter<ProductApdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(product: Product)
        {
            itemView.findViewById<ImageView>(R.id.product_Image).setImageResource(R.drawable.giay)
            itemView.findViewById<TextView>(R.id.product_name).text = product.product_name
            itemView.findViewById<TextView>(R.id.product_abb_name).text = product.product_abb_name
            itemView.findViewById<TextView>(R.id.product_count).text =
                product.product_price.toString()
            itemView.setOnClickListener {}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val context = parent.context
        val li = LayoutInflater.from(context)
        val contactView = li.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(mProducts[position])
    }

    override fun getItemCount() = mProducts.size
}

