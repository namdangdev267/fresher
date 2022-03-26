package com.misa.fresher.fragment.shipinformation.ship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.ItemShipCalculatorBinding
import com.misa.fresher.databinding.ItemShipRadiobuttonBinding
import com.misa.fresher.databinding.ItemShipTouchBinding
import com.misa.fresher.data.models.ItemShipInfor

class ShipAdapter(private val adapterData: MutableList<ItemShipInfor>) :
    RecyclerView.Adapter<ShipAdapter.ShipAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_CALCULATOR = 1
        private const val ITEM_RADIO_BUTTON = 2
    }

    abstract class ShipAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        open fun bindingData(item: ItemShipInfor) {
        }

        fun bindingItemTouch(item: ItemShipInfor.ItemTouch, binding: ItemShipTouchBinding) {
            binding.run {
                tvTouchTitle.text = item.title
                edtTouchHintContent.hint = item.hintContent
                item.imageResourcce?.let { imgTouch.setImageResource(it) }
                tvTouchRequire.text = item.require
            }
        }

        fun bindingItemCalculator(
            item: ItemShipInfor.ItemCalculator,
            binding: ItemShipCalculatorBinding
        ) {
            binding.run {
                tvCalculatorTitle.text = item.title
                tvCalculatorContent.text = item.content
            }
        }

        fun bindingItemRadioButton(
            item: ItemShipInfor.ItemRadioButton,
            binding: ItemShipRadiobuttonBinding
        ) {
            binding.run {
                radioOption1.text = item.option1
                radioOption2.text = item.option2
            }
        }
    }

    class ItemShipTouchViewholder(val binding: ItemShipTouchBinding) :
        ShipAdapter.ShipAdapterViewHolder(binding.root) {
        override fun bindingData(item: ItemShipInfor) {
            bindingItemTouch(item as ItemShipInfor.ItemTouch, binding)
        }
    }

    class ItemShipCalculatorViewholder(val binding: ItemShipCalculatorBinding) :
        ShipAdapter.ShipAdapterViewHolder(binding.root) {
        override fun bindingData(item: ItemShipInfor) {
            bindingItemCalculator(item as ItemShipInfor.ItemCalculator, binding)
        }
    }

    class ItemShipRadioViewholder(val binding: ItemShipRadiobuttonBinding) :
        ShipAdapter.ShipAdapterViewHolder(binding.root) {
        override fun bindingData(item: ItemShipInfor) {
            bindingItemRadioButton(item as ItemShipInfor.ItemRadioButton, binding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipAdapterViewHolder {
        return when (viewType) {
            ITEM_TOUCH -> ItemShipTouchViewholder(
                ItemShipTouchBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            ITEM_CALCULATOR -> ItemShipCalculatorViewholder(
                ItemShipCalculatorBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            ITEM_RADIO_BUTTON -> ItemShipRadioViewholder(
                ItemShipRadiobuttonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ItemShipTouchViewholder(
                ItemShipTouchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ShipAdapterViewHolder, position: Int) =
        holder.bindingData(adapterData[position])

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemShipInfor.ItemTouch -> ITEM_TOUCH
            is ItemShipInfor.ItemCalculator -> ITEM_CALCULATOR
            is ItemShipInfor.ItemRadioButton -> ITEM_RADIO_BUTTON
            else -> 0
        }
    }
}