package com.misa.fresher.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.SelectedProducts
import com.misa.fresher.showToast

/**
 * Tạo Adapter dùng cho RecyclerView trong màn BillDetail
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class ProductSelectedAdapter(
    var mSelectedProducts: MutableList<SelectedProducts>,
    val updateTotalPrice: (products: SelectedProducts) -> Unit
) :
    RecyclerView.Adapter<ProductSelectedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductSelectedAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bill_detail, parent, false)
        return ViewHolder(v, parent.context)
    }

    override fun onBindViewHolder(holder: ProductSelectedAdapter.ViewHolder, position: Int) {
        holder.bind(mSelectedProducts[position])
    }

    override fun getItemCount() = mSelectedProducts.size

    inner class ViewHolder(itemView: View, val contextVH: Context) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(selectedProducts: SelectedProducts) {
            itemView.findViewById<TextView>(R.id.tvProductId).text = selectedProducts.product.id
            itemView.findViewById<TextView>(R.id.tvProductName).text = selectedProducts.product.name
            itemView.findViewById<TextView>(R.id.tvProductPrice).text =
                "${selectedProducts.product.price}/cai"
            val tvAmount = itemView.findViewById<TextView>(R.id.tvProductAmont)
            val tvTotalPrice = itemView.findViewById<TextView>(R.id.tvTotalPrice)
            tvAmount.text = selectedProducts.amonut.toString()
            tvTotalPrice.text = "${selectedProducts.let { it.product.price * it.amonut }}"
            itemView.findViewById<ImageView>(R.id.ivAdd).setOnClickListener {
                selectedProducts.amonut++
                updateTotalPrice(selectedProducts)
            }
            itemView.findViewById<ImageView>(R.id.ivRemove).setOnClickListener {
                if (selectedProducts.amonut > 1) {
                    selectedProducts.amonut--
                    updateTotalPrice(selectedProducts)
                } else {
                    contextVH.showToast("Số lượng phải lớn hơn 0. Vui lòng kiểm tra lại")
                }
            }
        }
    }


}