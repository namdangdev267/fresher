package com.misa.fresher.views.fragments.shippingInfomation.Package

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemRecyclerView
import com.misa.fresher.R
import com.misa.fresher.databinding.ItemBillBinding
import com.misa.fresher.databinding.ItemShip3colBinding
import com.misa.fresher.databinding.ItemShipBinding
import com.misa.fresher.databinding.ItemShipTouchBinding

class PackageAdapter(private val adapterData: MutableList<ItemRecyclerView>) :
    RecyclerView.Adapter<PackageAdapter.PackageAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_3COL = 1
    }

    abstract class PackageAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        open fun bindData(item:ItemRecyclerView) {
        }

        fun bindItemTouch(item: ItemRecyclerView.ItemTouch, binding: ItemShipTouchBinding) {
            binding.textviewTouchTitle.text = item.title
            binding.edittextTouchHintContent.setText(
                item.hintContent
            )
            item.imageResourcce?.let {
                binding.imageviewTouch.setImageResource(it)
            }

            binding.textviewTouchRequire.text = item.require

        }

        fun bindItem3Col(item: ItemRecyclerView.Item3Col,binding: ItemShip3colBinding) {
            binding.textviewPackageSize.text = item.title
            binding.edittextPackageSizeContent1.setText(item.content1)
            binding.edittextPackageSizeContent2.setText(item.content2)
            binding.edittextPackageSizeContent3.setText(item.content3)
        }

    }

    class ItemTouchViewHolder(val binding:ItemShipTouchBinding):PackageAdapterViewHolder(binding.root)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItemTouch(item as ItemRecyclerView.ItemTouch,binding)
        }
    }

    class Item3ColViewHolder(val binding:ItemShip3colBinding):PackageAdapterViewHolder(binding.root)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItem3Col(item as ItemRecyclerView.Item3Col,binding)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageAdapterViewHolder {
        return when (viewType) {
            ITEM_TOUCH -> ItemTouchViewHolder(ItemShipTouchBinding.inflate(LayoutInflater.from(parent.context), parent,false))
            ITEM_3COL -> Item3ColViewHolder(ItemShip3colBinding.inflate(LayoutInflater.from(parent.context), parent,false))
            else -> ItemTouchViewHolder(ItemShipTouchBinding.inflate(LayoutInflater.from(parent.context), parent,false))
        }

    }

    override fun onBindViewHolder(holder: PackageAdapterViewHolder, position: Int) {
        holder.bindData(adapterData[position])
    }

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemRecyclerView.ItemTouch -> ITEM_TOUCH
            is ItemRecyclerView.Item3Col -> ITEM_3COL
            else -> 0
        }
    }
}