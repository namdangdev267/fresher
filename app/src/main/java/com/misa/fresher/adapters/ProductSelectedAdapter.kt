package com.misa.fresher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.Products
import com.misa.fresher.model.SelectedProducts


class ProductSelectedAdapter(
    private val mSelectedProducts: MutableList<SelectedProducts>,
    val updateTotalPrice: (products: SelectedProducts) -> Unit
) :
    RecyclerView.Adapter<ProductSelectedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductSelectedAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.item_bill_detail, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductSelectedAdapter.ViewHolder, position: Int) {
        holder.bind(mSelectedProducts[position])
    }

    override fun getItemCount() = mSelectedProducts.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(selectedProducts: SelectedProducts) {
            itemView.findViewById<TextView>(R.id.tvProductId).text = selectedProducts.product.id
            itemView.findViewById<TextView>(R.id.tvProductName).text = selectedProducts.product.name
            itemView.findViewById<TextView>(R.id.tvProductId).text = selectedProducts.product.id
            itemView.findViewById<TextView>(R.id.tvProductPrice).text =
                "${selectedProducts.product.price}/cai"
            val tvAmount = itemView.findViewById<TextView>(R.id.tvProductAmont)
            tvAmount.text = selectedProducts.amonut.toString()
            val tvTotalPrice = itemView.findViewById<TextView>(R.id.tvTotalPrice)
            tvTotalPrice.text = "${selectedProducts.let { it.product.price * it.amonut }}"
            itemView.findViewById<ImageView>(R.id.ivAdd).setOnClickListener {
                selectedProducts.amonut++
                tvAmount.text = selectedProducts.amonut.toString()
                tvTotalPrice.text = "${selectedProducts.let { it.product.price * it.amonut }}"
                updateTotalPrice(selectedProducts)
            }
            itemView.findViewById<ImageView>(R.id.ivRemove).setOnClickListener {
                if (selectedProducts.amonut > 1) {
                    selectedProducts.amonut--
                    tvAmount.text = selectedProducts.amonut.toString()
                    tvTotalPrice.text = "${selectedProducts.let { it.product.price * it.amonut }}"
                    updateTotalPrice(selectedProducts)
                }
            }

        }


    }
}