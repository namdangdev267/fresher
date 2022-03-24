package com.misa.fresher.views.fragments.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemProduct
import com.misa.fresher.R
import com.misa.fresher.databinding.ItemBillBinding
import com.misa.fresher.databinding.ItemSaleAndBillDetailBinding
import com.misa.fresher.views.customViews.CustomToast
import com.misa.fresher.views.fragments.bill.BillAdapter
import com.misa.fresher.views.fragments.billDetail.BillDetailAdapter

class SaleAdapter(
    private var listItemProduct: MutableList<ItemProduct>,
    val listener: (itemProduct: ItemProduct) -> Unit
) : RecyclerView.Adapter<SaleAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:ItemSaleAndBillDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemProduct: ItemProduct) {

            with(binding)
            {
                ivItemAdd.visibility = View.GONE
                ivItemRemove.visibility = View.GONE
                tvItemQuantity.visibility = View.GONE
                tvItemTotalPrice.visibility = View.GONE
                nameItem.text = itemProduct.name
                idItem.text = itemProduct.code
                priceItem.text = itemProduct.price.toString()
                imageItem.setImageResource(R.drawable.ic_shopping_bag)
                binding.root.setOnClickListener {
                    listener(itemProduct)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSaleAndBillDetailBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemProduct[position])
    }

    override fun getItemCount() = listItemProduct.size
}


