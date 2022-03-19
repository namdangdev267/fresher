package com.misa.fresher.views.fragments.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.models.enum.Color
import com.misa.fresher.R

class CustomRecyclerViewAdapter(
    private val listData: List<Color>,
    val clickColor: (color: Color) -> Unit
) : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, val clickColor: (color: Color) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(color: Color) {
            itemView.findViewById<TextView>(R.id.tv_cv_rcv_title).text = color.name.lowercase()
            itemView.setOnClickListener {
                clickColor(color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_custom_recyclerview, parent, false)
        return ViewHolder(view, clickColor)
    }

    override fun onBindViewHolder(holder: CustomRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size


}
