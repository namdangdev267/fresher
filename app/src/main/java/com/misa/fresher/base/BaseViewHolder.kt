package com.misa.fresher.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View):RecyclerView.ViewHolder(view) {
    abstract var clickItemListener: (T) -> Unit

    open fun bindData(item: T) {
        itemView.setOnClickListener { clickItemListener(item) }
    }
}