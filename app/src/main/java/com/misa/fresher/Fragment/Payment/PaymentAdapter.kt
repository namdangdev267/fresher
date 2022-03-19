package com.misa.fresher.Fragment.Payment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.CustomView.ToastCustom
import com.misa.fresher.Models.PackageProduct
import com.misa.fresher.R

class PaymentAdapter(
    private var listItemBillDetail: MutableList<PackageProduct>,
    private val listener: (itemBillDetail: PackageProduct) -> Unit
) :
    RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val listener: (itemBillDetail: PackageProduct) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(itemBillDetail: PackageProduct) {
            itemView.findViewById<TextView>(R.id.tvPriceProduct).text =
                itemBillDetail.getPrice().toString()
            itemView.findViewById<TextView>(R.id.tvCountProduct).text =
                itemBillDetail.countPackage.toString()
            itemView.findViewById<TextView>(R.id.tvNameProduct).text = itemBillDetail.nameProduct
            itemView.findViewById<TextView>(R.id.tvCodeProduct).text = itemBillDetail.idProduct
            itemView.findViewById<TextView>(R.id.tvPriceAndType).text =
                itemBillDetail.product.priceProduct.toString() + "/Package"
            itemView.findViewById<ImageView>(R.id.imgPackage).visibility = View.GONE

            itemView.findViewById<ImageView>(R.id.imgAdd).setOnClickListener {
                itemBillDetail.countPackage += 1
                listener(itemBillDetail)

            }

            itemView.findViewById<ImageView>(R.id.imgRemove).setOnClickListener {
                if (itemBillDetail.countPackage > 1) {
                    itemBillDetail.countPackage -= 1
                } else {
                    ToastCustom.makeToast(
                        itemView.context,
                        "Quantity must be more than 0. Please check again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                listener(itemBillDetail)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_package_rcv, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listItemBillDetail[position])

    override fun getItemCount() = listItemBillDetail.size
}


