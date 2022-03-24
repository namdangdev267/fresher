package com.misa.fresher.fragment.sale

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.ItemCustomRecyclerviewBinding
import com.misa.fresher.models.enum.Color

class CustomRCVAdapter(
    private val listData: List<Color>,
    val clickColor: (color: Color) -> Unit
) : RecyclerView.Adapter<CustomRCVAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemCustomRecyclerviewBinding,
        val clickColor: (color: Color) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(color: Color) {
            binding.tvCvRcvTitle.text = color.name.lowercase()
            binding.root.setOnClickListener {
                clickColor(color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, clickColor)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listData[position])

    override fun getItemCount() = listData.size
}
