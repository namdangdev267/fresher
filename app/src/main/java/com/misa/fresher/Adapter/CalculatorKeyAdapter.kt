package com.misa.fresher.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.Base.BaseAdapter
import com.misa.fresher.Base.BaseViewHolder
import com.misa.fresher.Model.CalculatorKey
import com.misa.fresher.databinding.ItemCalculatorKeyBinding

class CalculatorKeyAdapter(
    override var items: ArrayList<CalculatorKey>,
    override var clickItemListener: (CalculatorKey) -> Unit
) : BaseAdapter<CalculatorKey, CalculatorKeyAdapter.CalculatorKeyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CalculatorKeyViewHolder(
        ItemCalculatorKeyBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        clickItemListener
    )

    class CalculatorKeyViewHolder(
        private val binding: ItemCalculatorKeyBinding,
        override var clickItemListener: (CalculatorKey) -> Unit
    ) : BaseViewHolder<CalculatorKey>(binding.root) {

        override fun bindingData(item: CalculatorKey) {
            super.bindingData(item)
            binding.root.apply {
                value = item.value
                icon = item.icon
                isActive = item.isActive
            }
        }
    }
}