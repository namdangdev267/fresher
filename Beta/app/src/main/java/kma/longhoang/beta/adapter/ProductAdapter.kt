package kma.longhoang.beta.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.fragment.main.SaleFragment
import kma.longhoang.beta.model.ProductModel

class ProductAdapter(private val listProduct: MutableList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private val saleFragment = SaleFragment()
    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgProduct: ImageView = itemView.findViewById(R.id.img_product)
        var tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        var tvProductCode: TextView = itemView.findViewById(R.id.tv_product_code)
        var tvProductPrice: TextView = itemView.findViewById(R.id.tv_product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.imgProduct.setImageResource(R.drawable.ic_baseline_person_outline_24)
        holder.tvProductName.text = listProduct[position].name
        holder.tvProductCode.text = listProduct[position].code.toString()
        holder.tvProductPrice.text = listProduct[position].price.toString()
        holder.itemView.setOnClickListener {
            if (listProduct[position].color == null && listProduct[position].size == null && listProduct[position].unit == null) {
                addToOrderTemp()
            } else {
                showOptionDialog()
            }
        }
    }

    private fun showOptionDialog() {
    }

    @SuppressLint("ResourceAsColor")
    private fun addToOrderTemp() {
//        saleFragment.btnReset!!.findViewById<ImageButton>(R.id.button_reset).isClickable = true
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }
}