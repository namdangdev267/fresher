package com.misa.fresher.ui.shipinformation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.data.model.ShippingView

/**
 * Tạo adapter có mutil type item dùng để hiện thị view trong màn ShipInfor
 * Mới chỉ tạo view chưa xử lý.
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class ShipInforViewAdapter(
    private val adapterData: MutableList<ShippingView>
) :
    RecyclerView.Adapter<ShipInforViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShipInforViewAdapter.ViewHolder {
        val layout = when (viewType) {
            0 -> R.layout.shipping_info_touch_tv
            1 -> R.layout.shipping_info_touch_ed
            2 -> R.layout.shipping_info_two_col
            3 -> R.layout.shipping_info_checkbox
            4 -> R.layout.shipping_info_radiogroup
            else -> R.layout.shipping_info_three_col

        }
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ShipInforViewAdapter.ViewHolder, position: Int) {
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
        val tvReceiver = itemView.findViewById<TextView>(R.id.tvReceiver)
        val tvTextNotNull = itemView.findViewById<TextView>(R.id.tvNotNull)
        val ibtn = itemView.findViewById<ImageButton>(R.id.ibtnShipping)
        val edReceiver = itemView.findViewById<TextView>(R.id.edReceiverHint)

        val edReceiverHintType2 = itemView.findViewById<TextView>(R.id.edReceiverHintType2)
        val tvReceiverType2 = itemView.findViewById<TextView>(R.id.tvReceiverType2)
        val ibtnShippingType2 = itemView.findViewById<ImageButton>(R.id.ibtnShippingType2)

        val tvReceiverType3_method = itemView.findViewById<TextView>(R.id.tvReceiverType3_method)
        val tvReceiverType3_deposit = itemView.findViewById<TextView>(R.id.tvReceiverType3_deposit)
        val tvReceiverType3_method_context =
            itemView.findViewById<TextView>(R.id.tvReceiverType3_method_context)
        val tvReceiverType3_deposit_value =
            itemView.findViewById<TextView>(R.id.tvReceiverType3_deposit_value)
        val ibtnReceiverType3 = itemView.findViewById<ImageButton>(R.id.ibtnReceiverType3)

        val cbType4 = itemView.findViewById<CheckBox>(R.id.cbType4)

        val rbShipType1 = itemView.findViewById<TextView>(R.id.rbShipType1)
        val rbShipType2 = itemView.findViewById<TextView>(R.id.rbShipType2)

        val tvPackage1 = itemView.findViewById<TextView>(R.id.tvPackage1)
        val tvPackage2 = itemView.findViewById<TextView>(R.id.tvPackage2)
        val tvPackage3 = itemView.findViewById<TextView>(R.id.tvPackage3)
        val tvPackage4 = itemView.findViewById<TextView>(R.id.tvPackage4)
        private fun bindTouchTextView(item: ShippingView.TouchTextView) {
            tvReceiver.text = item.tittle
            edReceiver.hint = item.hint
            if (item.asterisk != null) {
                tvTextNotNull.text = item.asterisk
            }
            if (item.img != null) {
                ibtn.setImageResource(item.img)
            }
        }

        private fun bindTouchEditText(item: ShippingView.TouchEditText) {
            tvReceiverType2.text = item.tittle
            edReceiverHintType2.hint = item.hint
            if (item.img != null) {
                ibtnShippingType2.setImageResource(item.img)
            }
        }

        private fun bindTwoCol(item: ShippingView.TwoCol) {
            tvReceiverType3_method.text = item.tittlecol1
            tvReceiverType3_deposit.text = item.hintCol1
            tvReceiverType3_method_context.text =
                item.tittleCol2
            tvReceiverType3_deposit_value.text = item.hintCol2
            ibtnReceiverType3.setImageResource(item.img)
        }

        private fun bindCheckBox(item: ShippingView.CheckBox) {
            cbType4.text = item.context
        }

        private fun bindRadionGroup(item: ShippingView.RadionGroup) {
            rbShipType1.text = item.radionBtn1
            rbShipType2.text = item.radionBtn2
        }

        private fun bindThreeCol(item: ShippingView.ThreeCol) {
            tvPackage1.text = item.tittleCol1
            tvPackage2.text = item.hintCol1
            tvPackage3.text = item.hintCol1
            tvPackage4.text = item.hintCol3
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