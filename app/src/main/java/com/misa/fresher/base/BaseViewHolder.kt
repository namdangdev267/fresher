package com.misa.fresher.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract var clickItemListener: (T, Int) -> Unit

    open fun bindingData(item: T, position: Int) {
        itemView.setOnClickListener { clickItemListener(item, position) }
    }
}