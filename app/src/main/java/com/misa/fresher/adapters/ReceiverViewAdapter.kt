package com.misa.fresher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.ShippingView

class ReceiverViewAdapter(private val adapterData: MutableList<ShippingView>) :
    RecyclerView.Adapter<ReceiverViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReceiverViewAdapter.ViewHolder {
        val layout = when (viewType) {
            0 -> R.layout.shipping_info_touch_tv
            1 -> R.layout.shipping_info_touch_ed
            2 -> R.layout.shipping_info_two_col
            3 -> R.layout.shipping_info_cb
            4 -> R.layout.shipping_info_radiogroup
            else -> R.layout.shipping_info_three_col

        }
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReceiverViewAdapter.ViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount() = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is ShippingView.TouchTextView -> 0
            is ShippingView.TouchEditText -> 1
            is ShippingView.TwoCol -> 2
            is ShippingView.CheckBox -> 3
            is ShippingView.RadionGroup -> 4
            is ShippingView.ThreeCol -> 5
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun bindTouchTextView(item: ShippingView.TouchTextView) {
            itemView.findViewById<TextView>(R.id.tvReceiver).text = item.tittle
            itemView.findViewById<TextView>(R.id.tvReceiverHint).text = item.hint
            if (item.asterisk != null) {
                itemView.findViewById<TextView>(R.id.tvNotNull).text = item.asterisk
            }
            if (item.img != null) {
                itemView.findViewById<ImageButton>(R.id.ibtnShipping).setImageResource(item.img)
            }
        }

        private fun bindTouchEditText(item: ShippingView.TouchEditText) {
            itemView.findViewById<TextView>(R.id.tvReceiverType2).text = item.tittle
            itemView.findViewById<TextView>(R.id.tvReceiverHintType2).text = item.hint
            if (item.img != null) {
                itemView.findViewById<ImageButton>(R.id.ibtnShippingType2)
                    .setImageResource(item.img)
            }
        }

        private fun bindTwoCol(item: ShippingView.TwoCol) {
            itemView.findViewById<TextView>(R.id.tvReceiverType3_method).text = item.tittlecol1
            itemView.findViewById<TextView>(R.id.tvReceiverType3_deposit).text = item.hintCol1
            itemView.findViewById<TextView>(R.id.tvReceiverType3_method_context).text =
                item.tittleCol2
            itemView.findViewById<TextView>(R.id.tvReceiverType3_deposit_value).text = item.hintCol2
            itemView.findViewById<ImageButton>(R.id.ibtnReceiverType3).setImageResource(item.img)
        }

        private fun bindCheckBox(item: ShippingView.CheckBox) {
            itemView.findViewById<CheckBox>(R.id.cbType4).text = item.context
        }

        private fun bindRadionGroup(item: ShippingView.RadionGroup) {
            itemView.findViewById<TextView>(R.id.rbShipType1).text = item.radionBtn1
            itemView.findViewById<TextView>(R.id.rbShipType2).text = item.radionBtn2
        }

        private fun bindThreeCol(item: ShippingView.ThreeCol) {
            itemView.findViewById<TextView>(R.id.tvPackage1).text = item.tittleCol1
            itemView.findViewById<TextView>(R.id.tvPackage2).text = item.hintCol1
            itemView.findViewById<TextView>(R.id.tvPackage3).text = item.hintCol1
            itemView.findViewById<TextView>(R.id.tvPackage4).text = item.hintCol3
        }

        fun bind(dataModel: ShippingView) {
            when (dataModel) {
                is ShippingView.TouchTextView -> bindTouchTextView(dataModel)
                is ShippingView.TouchEditText -> bindTouchEditText(dataModel)
                is ShippingView.TwoCol -> bindTwoCol(dataModel)
                is ShippingView.CheckBox -> bindCheckBox(dataModel)
                is ShippingView.RadionGroup -> bindRadionGroup(dataModel)
                is ShippingView.ThreeCol -> bindThreeCol(dataModel)
            }
        }
    }
}