package kma.longhoang.beta.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.SaleViewModel
import kma.longhoang.beta.model.CustomerModel
import java.util.*

class CustomerAdapter(
    private val listCustomer: MutableList<CustomerModel>,
    private val itemClickListener: (customer: CustomerModel) -> Unit
) :
    RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvShortCustomer: TextView = itemView.findViewById<TextView>(R.id.tv_short_customer)
        var tvCustomer: TextView = itemView.findViewById(R.id.tv_customer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        )
    }

    override fun getItemCount() = listCustomer.size


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.tvShortCustomer.setBackgroundColor(color)
        holder.tvShortCustomer.text = listCustomer[position].name[0].uppercase()
        holder.tvCustomer.text = StringBuilder(listCustomer[position].name).append(" - ")
            .append(listCustomer[position].phone)
        holder.itemView.setOnClickListener {
            itemClickListener(listCustomer[position])
        }
    }
}