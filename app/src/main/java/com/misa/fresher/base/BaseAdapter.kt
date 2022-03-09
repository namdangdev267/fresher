package com.misa.fresher.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T,V : BaseViewHolder<T>>: RecyclerView.Adapter<V>() {
    abstract var adapterData : MutableList<T>

    abstract var clickItemListener: (T) -> Unit

    override fun getItemCount(): Int = adapterData.size

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bindData(adapterData[position])
    }
}