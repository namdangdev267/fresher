package com.misa.fresher.views.fragments.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.enum.Color
import com.misa.fresher.R
import com.misa.fresher.databinding.CustomRecyclerViewBinding
import com.misa.fresher.databinding.ItemBillBinding
import com.misa.fresher.databinding.ItemCustomRecyclerviewBinding
import com.misa.fresher.views.fragments.bill.BillAdapter

class CustomRecyclerViewAdapter(
    private val listData: List<Color>,
    val clickColor: (color: Color) -> Unit
) : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding:ItemCustomRecyclerviewBinding, val clickColor: (color: Color) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(color: Color) {
            binding.tvCvRcvTitle.text = color.name.lowercase()
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
