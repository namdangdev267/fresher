package com.misa.fresher.Base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract var clickItemListener: (T) -> Unit

    open fun bindingData(item: T) {
        itemView.setOnClickListener { clickItemListener(item) }
    }
}