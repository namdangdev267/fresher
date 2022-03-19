package kma.longhoang.beta.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.model.CustomerModel
import java.util.*

class CustomerAdapter(
    private val listCustomer: MutableList<CustomerModel>,
    private val itemClickListener: (customer: CustomerModel) -> Unit
) :
    RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgCustomer: ImageView = itemView.findViewById<ImageView>(R.id.img_customer)
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
        holder.imgCustomer.setBackgroundColor(color)
        holder.tvCustomer.text = StringBuilder(listCustomer[position].name).append(" - ")
            .append(listCustomer[position].phone)
        holder.itemView.setOnClickListener {
            itemClickListener(listCustomer[position])
        }
    }
}