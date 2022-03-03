package com.misa.fresher.Views.Fragments.ShippingInfomation.Package

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemShip
import com.misa.fresher.R

class PackageAdapter(private val adapterData: MutableList<ItemShip>) :
    RecyclerView.Adapter<PackageAdapter.PackageAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_3COL =1
    }

    class PackageAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun bindItemTouch(item: ItemShip.ItemTouch) {
            itemView.findViewById<TextView>(R.id.textview_touch_title).text = item.title
            itemView.findViewById<EditText>(R.id.edittext_touch_hint_content).hint =
                item.hintContent
            item.imageResourcce?.let {
                itemView.findViewById<ImageView>(R.id.imageview_touch).setImageResource(it)
            }
            itemView.findViewById<TextView>(R.id.textview_touch_require).text = item.require
        }

        private fun bindItem3Col(item: ItemShip.Item3Col) {
            itemView.findViewById<TextView>(R.id.textview_package_size).text = item.title
            itemView.findViewById<EditText>(R.id.edittext_package_size_content_1).hint = item.content1
            itemView.findViewById<EditText>(R.id.edittext_package_size_content_2).hint = item.content2
            itemView.findViewById<EditText>(R.id.edittext_package_size_content_3).hint = item.content3
        }


        fun bind(itemShip: ItemShip) {
            when (itemShip) {
                is ItemShip.ItemTouch -> bindItemTouch(itemShip)
                is ItemShip.Item3Col -> bindItem3Col(itemShip)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageAdapterViewHolder {
        val layout = when (viewType) {
            ITEM_TOUCH -> R.layout.item_ship_touch
            ITEM_3COL -> R.layout.item_ship_3col
            else -> R.layout.item_ship_touch
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return PackageAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PackageAdapterViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemShip.ItemTouch -> ITEM_TOUCH
            is ItemShip.Item3Col -> ITEM_3COL
            else -> 0
        }
    }
}