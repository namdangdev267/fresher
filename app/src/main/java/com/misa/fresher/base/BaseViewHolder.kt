package com.misa.fresher.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract var clickItem: (T) -> Unit

    open fun bindingData(item: T) =
        itemView.setOnClickListener { clickItem(item) }

}