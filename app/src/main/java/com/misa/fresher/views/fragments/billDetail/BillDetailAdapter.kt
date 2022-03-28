package com.misa.fresher.views.fragments.billDetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.base.BaseRecyclerViewAdapter
import com.misa.fresher.models.ItemBillDetail

import com.misa.fresher.databinding.ItemBillDetailBinding
import com.misa.fresher.models.ItemBill
import com.misa.fresher.views.customViews.CustomToast
import com.misa.fresher.views.fragments.SharedViewModel
import com.misa.fresher.views.fragments.bill.BillAdapter

class BillDetailAdapter(
    private val sharedViewModel: SharedViewModel
) : BaseRecyclerViewAdapter<ItemBillDetail, BillDetailAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemBillDetailBinding) :
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
                    sharedViewModel.updateQuantityOfItemBillDetail(itemBillDetail)
                }

                ivItemRemove.setOnClickListener {
                    if (itemBillDetail.quantity > 1) {
                        itemBillDetail.quantity -= 1
                        sharedViewModel.updateQuantityOfItemBillDetail(itemBillDetail)
                    }
                    else if(itemBillDetail.quantity==1)
                    {
                        CustomToast.makeText(itemView.context,"Quantity must be more than 0. Please check again")
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBillDetailBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}


