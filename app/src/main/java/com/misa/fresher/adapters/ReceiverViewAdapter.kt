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
            0 -> R.layout.shipping_item1
            1 -> R.layout.shipping_item2
            2 -> R.layout.shipping_item3
            3 -> R.layout.shipping_item4
            4 -> R.layout.shipping_item5
            else -> R.layout.shipping_item6

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
            is ShippingView.type1 -> 0
            is ShippingView.type2 -> 1
            is ShippingView.type3 -> 2
            is ShippingView.type4 -> 3
            is ShippingView.type5 -> 4
            is ShippingView.type6 -> 5
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun bindType1(item: ShippingView.type1) {
            itemView.findViewById<TextView>(R.id.tvReceiver).text = item.tittle
            itemView.findViewById<TextView>(R.id.tvReceiverHint).text = item.hint
            if (item.asterisk != null) {
                itemView.findViewById<TextView>(R.id.tvNotNull).text = item.asterisk
            }
            if (item.img != null) {
                itemView.findViewById<ImageButton>(R.id.ibtnShipping).setImageResource(item.img)
            }
        }

        private fun bindType2(item: ShippingView.type2) {
            itemView.findViewById<TextView>(R.id.tvReceiverType2).text = item.tittle
            itemView.findViewById<TextView>(R.id.tvReceiverHintType2).text = item.hint
            if (item.img != null) {
                itemView.findViewById<ImageButton>(R.id.ibtnShippingType2)
                    .setImageResource(item.img)
            }
        }

        private fun bindType3(item: ShippingView.type3) {
            itemView.findViewById<TextView>(R.id.tvReceiverType3_method).text = item.context1
            itemView.findViewById<TextView>(R.id.tvReceiverType3_deposit).text = item.context2
            itemView.findViewById<TextView>(R.id.tvReceiverType3_method_context).text =
                item.context3
            itemView.findViewById<TextView>(R.id.tvReceiverType3_deposit_value).text = item.context4
            itemView.findViewById<ImageButton>(R.id.ibtnReceiverType3).setImageResource(item.img)
        }

        private fun bindType4(item: ShippingView.type4) {
            itemView.findViewById<CheckBox>(R.id.cbType4).text = item.context
        }

        private fun bindType5(item: ShippingView.type5) {
            itemView.findViewById<TextView>(R.id.rbShipType1).text = item.context
            itemView.findViewById<TextView>(R.id.rbShipType2).text = item.context1
        }

        private fun bindType6(item: ShippingView.type6) {
            itemView.findViewById<TextView>(R.id.tvPackage1).text = item.context
            itemView.findViewById<TextView>(R.id.tvPackage2).text = item.context1
            itemView.findViewById<TextView>(R.id.tvPackage3).text = item.context2
            itemView.findViewById<TextView>(R.id.tvPackage4).text = item.context3
        }

        fun bind(dataModel: ShippingView) {
            when (dataModel) {
                is ShippingView.type1 -> bindType1(dataModel)
                is ShippingView.type2 -> bindType2(dataModel)
                is ShippingView.type3 -> bindType3(dataModel)
                is ShippingView.type4 -> bindType4(dataModel)
                is ShippingView.type5 -> bindType5(dataModel)
                is ShippingView.type6 -> bindType6(dataModel)
            }
        }
    }
}