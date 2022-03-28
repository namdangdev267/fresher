package com.misa.fresher.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R
import com.misa.fresher.models.SelectedProduct
import java.text.DecimalFormat

class BillAdapter(private val mListBill : ArrayList<SelectedProduct>, val mcontext : Context,val updateView : (product : SelectedProduct) -> Unit):
    RecyclerView.Adapter<BillAdapter.ViewHolder>() {

        inner class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
           fun bind(product: SelectedProduct){
               val billName = itemView.findViewById<TextView>(R.id.tv_BillName)
                   billName.text = product.product.productName
              val billMa =  itemView.findViewById<TextView>(R.id.tv_BillMa)
                  billMa.text  = product.product.productCode
              val billPrice =  itemView.findViewById<TextView>(R.id.tv_BillPrice)
                  billPrice.text = (product.product.price).toString()
               val sumPrice = itemView.findViewById<TextView>(R.id.tv_SumPrice)
                   sumPrice.text  = (product.amount * product.product.price).toString()
               val amountsl =itemView.findViewById<TextView>(R.id.tv_amount)
                   amountsl.text = product.amount.toString()

               val btnTang = itemView.findViewById<ImageButton>(R.id.imgbtn_cong_amount)
               val btnGiam = itemView.findViewById<ImageButton>(R.id.imgbtn_tru_amount)

               btnTang.setOnClickListener(){
                   product.amount++
                   amountsl.text = product.amount.toString()
                   sumPrice.text = (product.amount * product.product.price).toString()
                   updateView(product)
               }

               btnGiam.setOnClickListener(){
                   if(product.amount > 1){
                       product.amount --
                       amountsl.text = product.amount.toString()
                       sumPrice.text = (product.amount * product.product.price).toString()
                       updateView(product)
                   }
                   else if(product.amount == 1){
                       Toast.makeText(mcontext,"Số lượng cần giảm lớn hơn 1",Toast.LENGTH_SHORT).show()
                   }
               }
           }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
        val contactView = view.inflate(R.layout.item_bill,parent,false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mListBill[position])
    }

    override fun getItemCount(): Int {
        return mListBill.size
    }
}