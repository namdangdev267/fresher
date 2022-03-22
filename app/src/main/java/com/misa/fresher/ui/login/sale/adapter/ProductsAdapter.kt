package com.misa.fresher.ui.login.sale.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.ItemsListProductBinding
import com.misa.fresher.model.Products

/**
 * tạo ProductsAdapter để recyclerView màn sale sử dụng
 * @Auther : NTBao
 * @date : 3/16/2022
 **/
class ProductsAdapter(
    var mProducts: List<Products>,
    val clickItemListener: (products: Products) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemsListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvProductName
        val code = binding.tvProductId
        val price = binding.tvProductPrice
        val img = binding.ivProduct
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsListProductBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mProducts[position].name
        holder.code.text = mProducts[position].id
        holder.price.text = mProducts[position].price.toString()
        holder.img.setImageResource(mProducts[position].img)
        holder.binding.root.setOnClickListener {
            clickItemListener(mProducts[position])
        }
    }
    override fun getItemCount(): Int {
        return mProducts.size
    }
}