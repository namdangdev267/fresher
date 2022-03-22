package com.misa.fresher.views.fragments.shippingInfomation.Ship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.ItemRecyclerView
import com.misa.fresher.R
import com.misa.fresher.databinding.ItemShip3colBinding
import com.misa.fresher.databinding.ItemShipCalculatorBinding
import com.misa.fresher.databinding.ItemShipRadiobuttonBinding
import com.misa.fresher.databinding.ItemShipTouchBinding
import com.misa.fresher.views.fragments.shippingInfomation.Package.PackageAdapter

class ShipAdapter(private val adapterData: MutableList<ItemRecyclerView>) :
    RecyclerView.Adapter<ShipAdapter.ShipAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_CALCULATOR = 1
        private const val ITEM_RADIO_BUTTON = 2
    }

    abstract class ShipAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bindData(item: ItemRecyclerView)
        {
        }

        fun bindItemTouch(item: ItemRecyclerView.ItemTouch,binding: ItemShipTouchBinding) {
            binding.textviewTouchTitle.text = item.title
            binding.edittextTouchHintContent.setText(
                item.hintContent
            )
            item.imageResourcce?.let {
                binding.imageviewTouch.setImageResource(it)
            }

            binding.textviewTouchRequire.text = item.require
        }

        fun bindItemCalculator(item: ItemRecyclerView.ItemCalculator,binding: ItemShipCalculatorBinding) {
            binding.textviewCalculatorTitle.text = item.title
            binding.textviewCalculatorContent.text = item.content
        }

        fun bindItemRadioButton(item: ItemRecyclerView.ItemRadioButton,binding: ItemShipRadiobuttonBinding) {
            binding.radioOption1.text = item.option1
            binding.radioOption2.text = item.option2
        }
    }

    class ItemTouchViewHolder(val binding:ItemShipTouchBinding):
        ShipAdapter.ShipAdapterViewHolder(binding.root)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItemTouch(item as ItemRecyclerView.ItemTouch,binding)
        }
    }

    class ItemCalculatorViewHolder(val binding: ItemShipCalculatorBinding):
        ShipAdapter.ShipAdapterViewHolder(binding.root)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItemCalculator(item as ItemRecyclerView.ItemCalculator,binding)
        }
    }


    class ItemRadioViewHolder(val binding: ItemShipRadiobuttonBinding):
        ShipAdapter.ShipAdapterViewHolder(binding.root)
    {
        override fun bindData(item: ItemRecyclerView) {
            bindItemRadioButton(item as ItemRecyclerView.ItemRadioButton,binding)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipAdapterViewHolder {
        return when (viewType) {
            ITEM_TOUCH -> ItemTouchViewHolder(ItemShipTouchBinding.inflate(LayoutInflater.from(parent.context), parent,false))
            ITEM_CALCULATOR -> ItemCalculatorViewHolder(ItemShipCalculatorBinding.inflate(LayoutInflater.from(parent.context), parent,false))
            ITEM_RADIO_BUTTON -> ItemRadioViewHolder(ItemShipRadiobuttonBinding.inflate(LayoutInflater.from(parent.context), parent,false))
            else -> ItemTouchViewHolder(ItemShipTouchBinding.inflate(LayoutInflater.from(parent.context), parent,false))
        }
    }

    override fun onBindViewHolder(holder: ShipAdapterViewHolder, position: Int) {
        holder.bindData(adapterData[position])
    }

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemRecyclerView.ItemTouch -> ITEM_TOUCH
            is ItemRecyclerView.ItemCalculator -> ITEM_CALCULATOR
            is ItemRecyclerView.ItemRadioButton -> ITEM_RADIO_BUTTON
            else -> 0
        }
    }
}