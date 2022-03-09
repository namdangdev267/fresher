package com.misa.fresher.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.databinding.ItemDrawerBinding
import com.misa.fresher.model.DrawerItem

class DrawerAdapter(
    private val items: Array<DrawerItem>
) : RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder = DrawerViewHolder(
        ItemDrawerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) = holder.bindData(items[position])

    override fun getItemCount(): Int = items.size


    class DrawerViewHolder(
        private val itemBinding: ItemDrawerBinding
    ) : BaseViewHolder<DrawerItem>(itemBinding.root) {

        override fun bindData(data: DrawerItem) {
            itemBinding.root.setOnClickListener {
                data.onClickListener(data)
            }
            itemBinding.tvTitle.text = data.title
            itemBinding.ivIcon.setImageDrawable(data.icon)
        }

    }
}