package com.misa.fresher.ui.fragment.shipinfor.`package`

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.models.ItemShipInfor
import com.misa.fresher.databinding.ItemShip3colBinding
import com.misa.fresher.databinding.ItemShipTouchBinding

class PackageAdapter(private val adapterData: MutableList<ItemShipInfor>) :
    RecyclerView.Adapter<PackageAdapter.PackageAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_3COL = 1
    }

    abstract class PackageAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        open fun bindingData(itemShip: ItemShipInfor) {

        }

        fun bindingItemTouch(item: ItemShipInfor.ItemTouch, binding: ItemShipTouchBinding) {
            binding.run {
                tvTouchTitle.text = item.title
                edtTouchHintContent.setText(item.hintContent)
                item.imageResourcce?.let { imgTouch.setImageResource(it) }
                tvTouchRequire.text = item.require
            }
        }

        fun bindingItem3Col(item: ItemShipInfor.Item3Col, binding: ItemShip3colBinding) {
            binding.run {
                tvPackageSize.text = item.title
                edtPackageSizeContent1.setText(item.content1)
                edtPackageSizeContent2.setText(item.content2)
                edtPackageSizeContent3.setText(item.content3)

            }
        }

    }

    class ItemTouchViewholder(val binding: ItemShipTouchBinding) :
        PackageAdapter.PackageAdapterViewHolder(binding.root) {
        override fun bindingData(itemShip: ItemShipInfor) {
            bindingItemTouch(itemShip as ItemShipInfor.ItemTouch, binding)
        }
    }

    class ItemTouch3ColViewholder(val binding: ItemShip3colBinding) :
        PackageAdapter.PackageAdapterViewHolder(binding.root) {
        override fun bindingData(itemShip: ItemShipInfor) {
            bindingItem3Col(itemShip as ItemShipInfor.Item3Col, binding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageAdapterViewHolder {
        return when (viewType) {
            ITEM_TOUCH -> ItemTouchViewholder(
                ItemShipTouchBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            ITEM_3COL -> ItemTouch3ColViewholder(
                ItemShip3colBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> ItemTouchViewholder(
                ItemShipTouchBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: PackageAdapterViewHolder, position: Int) =
        holder.bindingData(adapterData[position])


    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemShipInfor.ItemTouch -> ITEM_TOUCH
            is ItemShipInfor.Item3Col -> ITEM_3COL
            else -> 0
        }
    }
}