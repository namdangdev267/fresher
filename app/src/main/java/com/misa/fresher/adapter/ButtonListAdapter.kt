package com.misa.fresher.adapter


import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.customview.BtnRound


class ButtonListAdapter(
    override var items: ArrayList<String>, var checked: Int = -1, override var clickItemListener: (String, Int) -> Unit
) : BaseAdapter<String, ButtonListAdapter.ViewHolder>() {

    val checkedItem: String
        get() = items[checked]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        BtnRound(parent.context, null), clickItemListener
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        super.onBindViewHolder(viewHolder, position)
        viewHolder.view.isActive = checked == position
    }

    class ViewHolder(
        val view: BtnRound, override var clickItemListener: (String, Int) -> Unit
    ) : BaseViewHolder<String>(view) {
        override fun bindingData(item: String, position: Int) {
            super.bindingData(item, position)
            view.value = item
        }
    }
}