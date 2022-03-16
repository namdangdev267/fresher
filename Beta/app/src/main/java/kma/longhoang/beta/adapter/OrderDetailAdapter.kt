package kma.longhoang.beta.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.SharedViewModel
import kma.longhoang.beta.model.OrderModel
import kma.longhoang.beta.model.ProductModel

class OrderDetailAdapter(
    private var orderList: MutableList<OrderModel>,
    private val orderViewModel: SharedViewModel
) :
    RecyclerView.Adapter<OrderDetailAdapter.OrderDetailHolder>() {

    class OrderDetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTotalPrice: TextView = itemView.findViewById(R.id.text_total_price)
        var tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        var tvProductCode: TextView = itemView.findViewById(R.id.tv_product_code)
        var tvProductPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        var tvAmount: TextView = itemView.findViewById(R.id.text_amount)
        var btnPlus: ImageButton = itemView.findViewById(R.id.button_plus)
        var btnMinus: ImageButton = itemView.findViewById(R.id.button_minus)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailAdapter.OrderDetailHolder {
        return OrderDetailHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_detail, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return orderList.size
    }


    override fun onBindViewHolder(holder: OrderDetailHolder, position: Int) {
        holder.tvProductName.text = orderList[position].name
        holder.tvProductCode.text = orderList[position].code
        holder.tvAmount.text = orderList[position].amount.toString()
        holder.tvProductPrice.text = orderList[position].price.toString()
        holder.tvTotalPrice.text =
            (orderList[position].amount * orderList[position].price).toString()
        holder.btnMinus.setOnClickListener {
            if (holder.tvAmount.text == "1") {
                Toast.makeText(
                    it.context,
                    "Số lượng phải lớn hơn 0. Hãy kiểm tra lại",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                orderList[position].amount -= 1
                holder.tvAmount.text = orderList[position].amount.toString()
                holder.tvTotalPrice.text =
                    (orderList[position].amount * orderList[position].price).toString()
                orderViewModel.setListOrder(orderList)
            }
        }
        holder.btnPlus.setOnClickListener {
            orderList[position].amount += 1
            holder.tvAmount.text = orderList[position].amount.toString()
            holder.tvTotalPrice.text =
                (orderList[position].amount * orderList[position].price).toString()
            orderViewModel.setListOrder(orderList)
        }

    }
}