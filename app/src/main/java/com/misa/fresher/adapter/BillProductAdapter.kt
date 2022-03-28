package com.misa.fresher.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.SelectedProduct
import com.misa.fresher.showToast
import java.text.DecimalFormat

class BillProductAdapter(
    private val mListBilll: ArrayList<SelectedProduct>, val mCtx: Context,
    val updateView: (product: SelectedProduct) -> Unit
) :
    RecyclerView.Adapter<BillProductAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvBillProductName = itemView.findViewById<TextView>(R.id.tvBillProductName)
        private val tvBillProductPrice = itemView.findViewById<TextView>(R.id.tvBillProductPrice)
        private val tvBillProductSKU = itemView.findViewById<TextView>(R.id.tvBillProductSKU)
        private val tvBillTotalPrice = itemView.findViewById<TextView>(R.id.tvBillTotalPrice)
        private val tvAmountProduct = itemView.findViewById<TextView>(R.id.tvAmountProduct)
        private val ibAddAmount = itemView.findViewById<ImageButton>(R.id.ibAddAmount)
        private val ibSubtractAmount = itemView.findViewById<ImageButton>(R.id.ibSubtractAmount)
        private val decimalFormat = DecimalFormat("0,000.0")
        fun bind(product: SelectedProduct) {
            tvBillProductName.text = product.product.productName
            tvBillProductSKU.text = product.product.productSKU
            tvBillProductPrice.text = decimalFormat.format(product.product.productPrice).toString()
            tvBillTotalPrice.text =
                decimalFormat.format(product.amount * product.product.productPrice)
                    .toString()
            tvAmountProduct.text = product.amount.toString()
            ibAddAmount.setOnClickListener {
                product.amount++
                tvAmountProduct.text = product.amount.toString()
                tvBillProductPrice.text =
                    decimalFormat.format(product.amount * product.product.productPrice).toString()
                updateView(product)
            }
            ibSubtractAmount.setOnClickListener {
                if (product.amount > 1) {
                    product.amount--
                    tvAmountProduct.text = product.amount.toString()
                    tvBillTotalPrice.text =
                        decimalFormat.format(product.amount * product.product.productPrice)
                            .toString()
                    updateView(product)
                } else if (product.amount == 1) {
                    mCtx.showToast("Số lượng không được ít hơn 0.Hãy kiểm tra lại")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val li = LayoutInflater.from(context)
        val contactView = li.inflate(R.layout.layout_item_bill, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(mListBilll[position])

    override fun getItemCount() = mListBilll.size
}