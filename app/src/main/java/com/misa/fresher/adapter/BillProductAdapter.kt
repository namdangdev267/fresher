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
import java.text.DecimalFormat

class BillProductAdapter(
    private val mListBilll: ArrayList<SelectedProduct>, val mCtx: Context,
    val updateView: (product: SelectedProduct) -> Unit
) :
    RecyclerView.Adapter<BillProductAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: SelectedProduct) {
            itemView.findViewById<TextView>(R.id.tvBillProductName).text =
                product.product.productName
            itemView.findViewById<TextView>(R.id.tvBillProductSKU).text = product.product.productSKU
            val decimalFormat = DecimalFormat("0,000.0")
            itemView.findViewById<TextView>(R.id.tvBillProductPrice).text =
                decimalFormat.format(product.product.productPrice).toString()
            itemView.findViewById<TextView>(R.id.tvBillTotalPrice).text =
                decimalFormat.format(product.amount * product.product.productPrice)
                    .toString()
            itemView.findViewById<TextView>(R.id.tvAmountProduct).text = product.amount.toString()
            itemView.findViewById<ImageButton>(R.id.ibAddAmount).setOnClickListener {
                product.amount++
                itemView.findViewById<TextView>(R.id.tvAmountProduct).text =
                    product.amount.toString()
                itemView.findViewById<TextView>(R.id.tvBillTotalPrice).text =
                    decimalFormat.format(product.amount * product.product.productPrice).toString()
                updateView(product)
            }
            itemView.findViewById<ImageButton>(R.id.ibSubtractAmount).setOnClickListener {
                if (product.amount > 1) {
                    product.amount--
                    itemView.findViewById<TextView>(R.id.tvAmountProduct).text =
                        product.amount.toString()
                    itemView.findViewById<TextView>(R.id.tvBillTotalPrice).text =
                        decimalFormat.format(product.amount * product.product.productPrice)
                            .toString()
                    updateView(product)
                }
                else if(product.amount==1)
                {
                    val toast=Toast.makeText(mCtx,"Số lượng không được ít hơn 0.Hãy kiểm tra lại",Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP,0,0)
                    toast.show()
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
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mListBilll[position])
    }
    override fun getItemCount() = mListBilll.size
}