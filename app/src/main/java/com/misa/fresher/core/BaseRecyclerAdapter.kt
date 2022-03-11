package com.misa.fresher.core

import androidx.recyclerview.widget.RecyclerView

/**
 * Base class cho các Recycler Adapter
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 1
 * @updated 3/10/2022: Tạo class
 */
abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>>(
    protected val items: MutableList<T>
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bindData(items[position])
}