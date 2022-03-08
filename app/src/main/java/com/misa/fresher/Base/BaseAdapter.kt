package com.misa.fresher.Base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : BaseViewHolder<T>> : RecyclerView.Adapter<V>() {
    abstract var items: ArrayList<T>
    abstract var clickItemListener: (T) -> Unit

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: V, position: Int) {
        viewHolder.apply {
            bindingData(items[position])
        }
    }
}