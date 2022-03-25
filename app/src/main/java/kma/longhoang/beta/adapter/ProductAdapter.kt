package kma.longhoang.beta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kma.longhoang.beta.R
import kma.longhoang.beta.model.ProductModel

class ProductAdapter(
    private var listProduct: MutableList<ProductModel>,
    val clickItemListener: (products: ProductModel) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

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
        holder.tvProductCode.text = listProduct[position].code
        holder.tvProductPrice.text = listProduct[position].price.toString()
        holder.itemView.setOnClickListener {
            clickItemListener(listProduct[position])
        }
    }

    override fun getItemCount() = listProduct.size
}