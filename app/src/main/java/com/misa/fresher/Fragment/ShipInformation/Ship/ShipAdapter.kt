package com.misa.fresher.Fragment.ShipInformation.Ship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemShipInfor
import com.misa.fresher.R

class ShipAdapter(private val adapterData: MutableList<ItemShipInfor>) :
    RecyclerView.Adapter<ShipAdapter.ShipAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_CALCULATOR = 1
        private const val ITEM_RADIO_BUTTON = 2
    }

    class ShipAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun bindItemTouch(item: ItemShipInfor.ItemTouch) {
            itemView.findViewById<TextView>(R.id.textview_touch_title).text = item.title
            itemView.findViewById<EditText>(R.id.edittext_touch_hint_content).hint =
                item.hintContent
            item.imageResourcce?.let {
                itemView.findViewById<ImageView>(R.id.imageview_touch).setImageResource(it)
            }
            itemView.findViewById<TextView>(R.id.textview_touch_require).text = item.require
        }

        private fun bindItemCalculator(item: ItemShipInfor.ItemCalculator) {
            itemView.findViewById<TextView>(R.id.textview_calculator_title).text = item.title
            itemView.findViewById<TextView>(R.id.textview_calculator_content).text = item.content
        }

        private fun bindItemRadioButton(item: ItemShipInfor.ItemRadioButton) {
            itemView.findViewById<TextView>(R.id.radio_option1).text = item.option1
            itemView.findViewById<TextView>(R.id.radio_option2).text = item.option2

        }

        fun bind(itemShip: ItemShipInfor) {
            when (itemShip) {
                is ItemShipInfor.ItemTouch -> bindItemTouch(itemShip)
                is ItemShipInfor.ItemCalculator -> bindItemCalculator(itemShip)
                is ItemShipInfor.ItemRadioButton -> bindItemRadioButton(itemShip)
                else -> {}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipAdapterViewHolder {
        val layout = when (viewType) {
            ITEM_TOUCH -> R.layout.item_ship_touch
            ITEM_CALCULATOR -> R.layout.item_ship_calculator
            ITEM_RADIO_BUTTON -> R.layout.item_ship_radiobutton
            else -> R.layout.item_ship_touch
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShipAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShipAdapterViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

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