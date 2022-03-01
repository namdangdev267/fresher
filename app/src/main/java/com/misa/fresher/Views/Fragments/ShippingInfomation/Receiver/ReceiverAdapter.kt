package com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.ItemShip
import com.misa.fresher.R

class ReceiverAdapter(private val adapterData: MutableList<ItemShip>):RecyclerView.Adapter<ReceiverAdapter.ReceiverAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_CALCULATOR = 1
        private const val ITEM_MULTICONTENT = 2
        private const val ITEM_CHECK = 3
    }

    class ReceiverAdapterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private fun bindItemTouch(item: ItemShip.ItemTouch){
            itemView.findViewById<TextView>(R.id.textview_touch_title).text= item.title
            itemView.findViewById<TextView>(R.id.textview_touch_hint_content).text= item.hintContent
            item.imageResourcce?.let {
                itemView.findViewById<ImageView>(R.id.imageview_touch).setImageResource(it)
            }
            itemView.findViewById<TextView>(R.id.textview_touch_asterisk).text=item.asterisk
        }

        private fun bindItemCalculator(item: ItemShip.ItemCalculator){
            itemView.findViewById<TextView>(R.id.textview_calculator_title).text= item.title
            itemView.findViewById<TextView>(R.id.textview_calculator_content).text= item.content
//            itemView.findViewById<ImageView>(R.id.imageview_touch).setImageResource(R.drawable.ic_calculator)
        }

        private fun bindItemMulticontent(item: ItemShip.ItemMultiContent){
            itemView.findViewById<TextView>(R.id.textview_multicontent_title1).text= item.title1
            itemView.findViewById<TextView>(R.id.textview_multicontent_title2).text= item.title2
            itemView.findViewById<TextView>(R.id.textview_multicontent_content1).text= item.content1
            itemView.findViewById<TextView>(R.id.textview_multicontent_content2).text= item.content2
            itemView.findViewById<ImageView>(R.id.imageview_multicontent).setImageResource(item.imageResource)
        }

        private fun bindItemcheck(item: ItemShip.ItemCheck){
            itemView.findViewById<TextView>(R.id.textview_check_title).text= item.title
        }

        fun bind(itemShip:ItemShip){
            when (itemShip){
                is ItemShip.ItemTouch -> bindItemTouch(itemShip)
                is ItemShip.ItemCalculator -> bindItemCalculator(itemShip)
                is ItemShip.ItemMultiContent -> bindItemMulticontent(itemShip)
                is ItemShip.ItemCheck -> bindItemcheck(itemShip)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiverAdapterViewHolder {
        val layout = when(viewType){
            ITEM_TOUCH -> R.layout.item_ship_touch
            ITEM_CALCULATOR -> R.layout.item_ship_calculator
            ITEM_MULTICONTENT  -> R.layout.item_ship_multicontent
            ITEM_CHECK -> R.layout.item_ship_check
            else -> R.layout.item_ship_touch
        }

        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return ReceiverAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReceiverAdapterViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when(adapterData[position]){
            is ItemShip.ItemTouch -> ITEM_TOUCH
            is ItemShip.ItemCalculator -> ITEM_CALCULATOR
            is ItemShip.ItemMultiContent -> ITEM_MULTICONTENT
            is ItemShip.ItemCheck -> ITEM_CHECK
            else -> 0
        }
    }
}