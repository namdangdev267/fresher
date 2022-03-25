package com.misa.fresher.views.fragments.sale

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.enums.Color
import com.misa.fresher.databinding.ItemCustomRecyclerviewBinding

class CustomRecyclerViewAdapter(
    private val listData: List<String>,
    val clickColor: (color: String) -> Unit
) : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding:ItemCustomRecyclerviewBinding, val clickColor: (color: String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(color: String) {
            binding.tvCvRcvTitle.text = color.lowercase()
            binding.root.setOnClickListener {
                clickColor(color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding,clickColor)

    }

    override fun onBindViewHolder(holder: CustomRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size


}
