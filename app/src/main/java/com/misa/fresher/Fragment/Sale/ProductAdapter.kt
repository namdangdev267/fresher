package com.misa.fresher.fragment.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.Product
import com.misa.fresher.R

open class ProductAdapter(
    private val productList: MutableList<Product>,
    private val onClickItemListener: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View, val listener: (itemProduct: Product) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(itemProduct: Product) {
            itemView.findViewById<ImageView>(R.id.imgAdd).visibility = View.GONE
            itemView.findViewById<ImageView>(R.id.imgRemove).visibility = View.GONE
            itemView.findViewById<TextView>(R.id.tvCountProduct).visibility = View.GONE
            itemView.findViewById<TextView>(R.id.tvPriceProduct).visibility = View.GONE

            itemView.findViewById<TextView>(R.id.tvNameProduct).text = itemProduct.nameProduct
            itemView.findViewById<TextView>(R.id.tvCodeProduct).text = itemProduct.codeProduct
            itemView.findViewById<TextView>(R.id.tvPriceAndType).text = itemProduct.priceProduct.toString()
            itemView.findViewById<ImageView>(R.id.imgPackage)
                .setImageResource(R.drawable.ic_launcher_foreground)
            itemView.setOnClickListener {
                listener(itemProduct)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val productView = LayoutInflater.from(parent.context).inflate(R.layout.item_package_rcv, parent, false)
        return MyViewHolder(productView, onClickItemListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(productList[position])

    override fun getItemCount(): Int = productList.size
}
