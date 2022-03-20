package com.misa.fresher.Fragment.ShipInformation.Receiver

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.Models.InforShip
import com.misa.fresher.Models.ItemShipInfor
import com.misa.fresher.R

class ReceiverAdapter(
    private val adapterData: MutableList<ItemShipInfor>,
    val changeEditText: (inforShip: InforShip) -> Unit
) :
    RecyclerView.Adapter<ReceiverAdapter.ReceiverAdapterViewHolder>() {

    companion object {
        private const val ITEM_TOUCH = 0
        private const val ITEM_CALCULATOR = 1
        private const val ITEM_MULTICONTENT = 2
        private const val ITEM_CHECK = 3
    }

    inner class ReceiverAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun bindItemTouch(item: ItemShipInfor.ItemTouch) {
            val tvTitle = itemView.findViewById<TextView>(R.id.textview_touch_title)
            val editText = itemView.findViewById<EditText>(R.id.edittext_touch_hint_content)
            itemView.findViewById<TextView>(R.id.textview_touch_require).text = item.require
            item.imageResourcce?.let {
                itemView.findViewById<ImageView>(R.id.imageview_touch).setImageResource(it)
            }

            tvTitle.text = item.title
            editText.hint = item.hintContent

            if (item.title == "Tel") {
                editText.inputType = InputType.TYPE_CLASS_NUMBER
            }

            val inforShip = InforShip(null, null, null, null, null, null, null, null, null, false)

            editText.doAfterTextChanged {
                if (item.title == "Receiver") {
                    inforShip.receiver = it.toString()
                    changeEditText(inforShip)
                } else if (item.title == "Tel") {
                    inforShip.tel = it.toString()
                    changeEditText(inforShip)
                } else if (item.title == "Address") {
                    inforShip.address = it.toString()
                    changeEditText(inforShip)
                }
            }
        }

        private fun bindItemCalculator(item: ItemShipInfor.ItemCalculator) {
            itemView.findViewById<TextView>(R.id.textview_calculator_title).text = item.title
            itemView.findViewById<TextView>(R.id.textview_calculator_content).text = item.content
        }

        private fun bindItemMulticontent(item: ItemShipInfor.ItemMultiContent) {
            itemView.findViewById<TextView>(R.id.textview_multicontent_title1).text = item.title1
            itemView.findViewById<TextView>(R.id.textview_multicontent_title2).text = item.title2
            itemView.findViewById<TextView>(R.id.textview_multicontent_content1).text =
                item.content1
            itemView.findViewById<TextView>(R.id.textview_multicontent_content2).text =
                item.content2
            itemView.findViewById<ImageView>(R.id.imageview_multicontent)
                .setImageResource(item.imageResource)
        }

        private fun bindItemcheck(item: ItemShipInfor.ItemCheck) {
            itemView.findViewById<TextView>(R.id.textview_check_title).text = item.title
        }

        fun bind(itemShip: ItemShipInfor) {
            when (itemShip) {
                is ItemShipInfor.ItemTouch -> bindItemTouch(itemShip)
                is ItemShipInfor.ItemCalculator -> bindItemCalculator(itemShip)
                is ItemShipInfor.ItemMultiContent -> bindItemMulticontent(itemShip)
                is ItemShipInfor.ItemCheck -> bindItemcheck(itemShip)
                else -> {}
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReceiverAdapter.ReceiverAdapterViewHolder {
        val layout = when (viewType) {
            ITEM_TOUCH -> R.layout.item_ship_touch
            ITEM_CALCULATOR -> R.layout.item_ship_calculator
            ITEM_MULTICONTENT -> R.layout.item_ship_multicontent
            ITEM_CHECK -> R.layout.item_ship_check
            else -> R.layout.item_ship_touch
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ReceiverAdapterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ReceiverAdapter.ReceiverAdapterViewHolder,
        position: Int
    ) =
        holder.bind(adapterData[position])

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ItemShipInfor.ItemTouch -> ITEM_TOUCH
            is ItemShipInfor.ItemCalculator -> ITEM_CALCULATOR
            is ItemShipInfor.ItemMultiContent -> ITEM_MULTICONTENT
            is ItemShipInfor.ItemCheck -> ITEM_CHECK
            else -> 0
        }
    }
}