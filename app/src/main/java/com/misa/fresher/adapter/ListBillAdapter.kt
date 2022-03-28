package com.misa.fresher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.model.BillInfor
import java.text.DecimalFormat

class ListBillAdapter(
    private val mListBill: ArrayList<BillInfor>,
) :
    RecyclerView.Adapter<ListBillAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvBillNum =itemView.findViewById<TextView>(R.id.tvBillNum)
        private val decimalFormat = DecimalFormat("0,000.0")
        private val tvBillAmount=itemView.findViewById<TextView>(R.id.tvBillAmount)
        fun bind(billInfor: BillInfor) {
            tvBillNum.text = billInfor.billNum.toString()
            tvBillAmount.text = decimalFormat.format(billInfor.total).toString()
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBillAdapter.ViewHolder {
        val context = parent.context
        val li = LayoutInflater.from(context)
        val contactView = li.inflate(R.layout.list_bill_item_layout, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount() = mListBill.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mListBill[position])
    }
}