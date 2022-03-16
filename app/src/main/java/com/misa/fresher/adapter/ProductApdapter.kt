package com.misa.fresher.adapter

import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.model.Product
import java.text.DecimalFormat
import java.text.NumberFormat

class ProductApdapter(
    override var items: ArrayList<Product>,
    override var clickItemListener: (Product) -> Unit
) : BaseAdapter<Product, ProductApdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false),
        clickItemListener
    )

    class ProductViewHolder(itemView: View, override var clickItemListener: (Product) -> Unit) :
        BaseViewHolder<Product>(itemView) {
        override fun bindData(item: Product) {
            super.bindData(item)
            itemView.apply {
                this.findViewById<ImageView>(R.id.imgProductImage).setImageResource(R.drawable.giay)
                this.findViewById<TextView>(R.id.tvProductName).text = item.productName
                this.findViewById<TextView>(R.id.tvProductSKU).text = item.productSKU
                val decimalFormat=DecimalFormat("0,000.00")
                this.findViewById<TextView>(R.id.tvProductPrice).text = decimalFormat.format(item.productPrice).toString()
            }
        }
    }
}

