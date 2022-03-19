package kma.longhoang.beta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.model.BillModel

class ListBillAdapter(private val listBill: MutableList<BillModel>):
    RecyclerView.Adapter<ListBillAdapter.ListBillHolder>(){

    inner class ListBillHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvBillId: TextView = itemView.findViewById<TextView>(R.id.text_bill_id)
        var tvBillCustomer: TextView = itemView.findViewById<TextView>(R.id.text_bill_customer)
        var tvBillPrice: TextView = itemView.findViewById<TextView>(R.id.text_bill_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBillHolder {
        return ListBillHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bill, parent, false))
    }

    override fun onBindViewHolder(holder: ListBillHolder, position: Int) {
        holder.tvBillId.text = listBill[position].id.toString()
        if (listBill[position].customer!= null){
            holder.tvBillCustomer.hint  = StringBuilder(""+listBill[position].customer?.name).append(" - ").append(listBill[position].customer?.phone)
        }else
            holder.tvBillCustomer.isVisible = false
        val totalPrice = listBill[position].orderList?.map { it.amount * it.price}?.sum()
        holder.tvBillPrice.text = totalPrice.toString()
    }

    override fun getItemCount()= listBill.size
}