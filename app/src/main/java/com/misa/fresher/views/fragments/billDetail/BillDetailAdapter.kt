package com.misa.fresher.views.fragments.billDetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.R
import com.misa.fresher.databinding.ItemBillBinding
import com.misa.fresher.databinding.ItemSaleAndBillDetailBinding
import com.misa.fresher.showToastUp
import com.misa.fresher.showToastUp
import com.misa.fresher.views.customViews.CustomToast
import com.misa.fresher.views.fragments.bill.BillAdapter

class BillDetailAdapter(
    private var listItemBillDetail: MutableList<ItemBillDetail>,
    val listener: (itemBillDetail: ItemBillDetail) -> Unit
) : RecyclerView.Adapter<BillDetailAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemSaleAndBillDetailBinding, val listener: (itemBillDetail: ItemBillDetail) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(itemBillDetail: ItemBillDetail) {
            with(binding)
            {
                tvItemTotalPrice.text = (itemBillDetail.getAllPrice().toString())
                tvItemQuantity.text = itemBillDetail.quantity.toString()
                nameItem.text = itemBillDetail.name
                idItem.text = itemBillDetail.code
                priceItem.text = itemBillDetail.itemProduct?.price.toString() + "/Package"
                imageItem.visibility = View.GONE
                ivItemAdd.setOnClickListener {
                    itemBillDetail.quantity += 1
                    listener(itemBillDetail)
                }

                ivItemRemove.setOnClickListener {
                    if (itemBillDetail.quantity > 1) {
                        itemBillDetail.quantity -= 1
                        listener(itemBillDetail)
                    }
                    else if(itemBillDetail.quantity==1)
                    {
                        CustomToast.makeText(itemView.context,"Quantity must be more than 0. Please check again")
                    }
                }
            }

            binding.ivItemRemove.setOnClickListener {
                if (itemBillDetail.quantity > 1) {
                    itemBillDetail.quantity -= 1
                    listener(itemBillDetail)
                }
                else if(itemBillDetail.quantity==1)
                {
                    CustomToast.makeText(itemView.context,"Quantity must be more than 0. Please check again")
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSaleAndBillDetailBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemBillDetail[position])
    }

    override fun getItemCount() = listItemBillDetail.size
}


