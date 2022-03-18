package com.misa.fresher.ui.shipinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.databinding.*
import com.misa.fresher.data.model.ShipInfo
import com.misa.fresher.utils.Enums

class ShipInfoAdapter(
    override var items: ArrayList<ArrayList<ShipInfo>>,
    override var clickItemListener: (ArrayList<ShipInfo>, Int) -> Unit
) : BaseAdapter<ArrayList<ShipInfo>, ShipInfoAdapter.ShipInfoViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position].size) {
            2 -> Enums.ShipInfo.BASIC_TWO.ordinal
            3 -> Enums.ShipInfo.BASIC_THREE.ordinal
            else -> items[position][0].type.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipInfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Enums.ShipInfo.BASIC_TWO.ordinal -> {
                TwoColumnsViewHolder(
                    ItemShipInfoTwoBinding.inflate(layoutInflater, parent, false),
                    clickItemListener
                )
            }
            Enums.ShipInfo.BASIC_THREE.ordinal -> {
                ThreeColumnsViewHolder(
                    ItemShipInfoThreeBinding.inflate(layoutInflater, parent, false),
                    clickItemListener
                )
            }
            Enums.ShipInfo.CHECKBOX.ordinal -> {
                CheckboxViewHolder(
                    ItemShipInfoCheckboxBinding.inflate(layoutInflater, parent, false),
                    clickItemListener
                )
            }
            Enums.ShipInfo.RADIO.ordinal -> {
                RadioViewHolder(
                    ItemShipInfoRadioBinding.inflate(layoutInflater, parent, false),
                    clickItemListener
                )
            }
            else -> {
                BasicViewHolder(
                    ItemShipInfoBasicBinding.inflate(layoutInflater, parent, false),
                    clickItemListener
                )
            }
        }
    }


    abstract class ShipInfoViewHolder(view: View)
        : BaseViewHolder<ArrayList<ShipInfo>>(view) {

        fun onBindBasicData(shipInfo: ShipInfo, binding: ItemShipInfoBasicBinding) {
            if (shipInfo is ShipInfo.Basic) {
                binding.apply {
                    title.text = shipInfo.title
                    content.hint = shipInfo.hint
                    content.setText(shipInfo.content)
                    isRequired.text = if (shipInfo.isRequired) "*" else ""
                    content.inputType = shipInfo.inputType
                    shipInfo.endIcon.let { endAction.setImageResource(it) }
                }
            }
        }

        fun onBindRadioData(shipInfo: ShipInfo, binding: ItemShipInfoRadioBinding) {
            if (shipInfo is ShipInfo.Radio) {
                arrayListOf(binding.radio1, binding.radio2).forEachIndexed { index, radioButton ->
                    radioButton.text = shipInfo.contents[index]
                    radioButton.isChecked = index == shipInfo.checked
                }
            }
        }

        fun onBindCheckboxData(shipInfo: ShipInfo, binding: ItemShipInfoCheckboxBinding) {
            if (shipInfo is ShipInfo.Checkbox) {
                binding.checkbox.isChecked = shipInfo.isChecked
                binding.checkbox.text = shipInfo.content
            }
        }
    }

    class BasicViewHolder(
        private val binding: ItemShipInfoBasicBinding,
        override var clickItemListener: (ArrayList<ShipInfo>, Int) -> Unit
    ) : ShipInfoViewHolder(binding.root) {
        override fun bindingData(item: ArrayList<ShipInfo>, position: Int) {
            super.bindingData(item, position)
            onBindBasicData(item[0], binding)
        }
    }

    class TwoColumnsViewHolder(
        private val binding: ItemShipInfoTwoBinding,
        override var clickItemListener: (ArrayList<ShipInfo>, Int) -> Unit
    ) : ShipInfoViewHolder(binding.root) {
        override fun bindingData(item: ArrayList<ShipInfo>, position: Int) {
            super.bindingData(item, position)
            listOf(binding.col1, binding.col2).forEachIndexed { i, col ->
                onBindBasicData(item[i], col)
            }
        }

    }

    class ThreeColumnsViewHolder(
        private val binding: ItemShipInfoThreeBinding,
        override var clickItemListener: (ArrayList<ShipInfo>, Int) -> Unit
    ) :
        ShipInfoViewHolder(binding.root) {

        override fun bindingData(item: ArrayList<ShipInfo>, position: Int) {
            super.bindingData(item, position)
            listOf(binding.col1, binding.col2, binding.col3).forEachIndexed { i, col ->
                onBindBasicData(item[i], col)
            }
        }
    }

    class CheckboxViewHolder(
        private val binding: ItemShipInfoCheckboxBinding,
        override var clickItemListener: (ArrayList<ShipInfo>, Int) -> Unit
    ) :
        ShipInfoViewHolder(binding.root) {
        override fun bindingData(item: ArrayList<ShipInfo>, position: Int) {
            super.bindingData(item, position)
            onBindCheckboxData(item[0], binding)
        }
    }

    class RadioViewHolder(
        private val binding: ItemShipInfoRadioBinding,
        override var clickItemListener: (ArrayList<ShipInfo>, Int) -> Unit
    ) :
        ShipInfoViewHolder(binding.root) {
        override fun bindingData(item: ArrayList<ShipInfo>, position: Int) {
            super.bindingData(item, position)
            onBindRadioData(item[0], binding)
        }
    }
}