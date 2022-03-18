package com.misa.fresher.ui.calculator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.databinding.ItemCalculatorKeyBinding
import com.misa.fresher.models.CalculatorKey

class CalculatorKeyAdapter(
    override var items: ArrayList<CalculatorKey>,
    override var clickItemListener: (CalculatorKey, Int) -> Unit
) : BaseAdapter<CalculatorKey, CalculatorKeyAdapter.CalculatorKeyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CalculatorKeyViewHolder(
        ItemCalculatorKeyBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        clickItemListener
    )

    class CalculatorKeyViewHolder(
        private val binding: ItemCalculatorKeyBinding,
        override var clickItemListener: (CalculatorKey, Int) -> Unit
    ) : BaseViewHolder<CalculatorKey>(binding.root) {
        override fun bindingData(item: CalculatorKey, position: Int) {
            super.bindingData(item, position)
            binding.root.apply {
                value = item.value
                icon = item.icon
                isActive = item.isActive
            }
        }
    }
}