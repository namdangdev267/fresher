package com.misa.fresher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.models.Bill

class ListOfBillApdater(private val mListBill : ArrayList<Bill>) :
    RecyclerView.Adapter<ListOfBillApdater.ViewHolder>() {

        inner class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
            fun bind(billInfor: Bill) {
               var  idListBill =  itemView.findViewById<TextView>(R.id.tv_idlistofbill)
                    idListBill.text  = billInfor.billNumber.toString()
               var tvSumOfList = itemView.findViewById<TextView>(R.id.tv_sumlistofbill)
                   tvSumOfList.text = billInfor.total.toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
        val contactView = view.inflate(R.layout.item_listofbill, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mListBill[position])
    }

    override fun getItemCount(): Int {
        return mListBill.size
    }
}