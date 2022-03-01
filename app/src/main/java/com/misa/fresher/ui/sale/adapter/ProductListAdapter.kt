package com.misa.fresher.ui.sale.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.ItemProductBinding
import com.misa.fresher.model.Product
import com.misa.fresher.util.toCurrency

class ProductListAdapter(
    private val onClickListener: (Product) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private var products = mutableListOf<Product>()

    fun updateProduct(list: MutableList<Product>) {
        products = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickListener
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(
        private val itemBinding: ItemProductBinding,
        private val onClickListener: (Product) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(product: Product) {
            itemBinding.root.setOnClickListener {
                onClickListener(product)
            }

            itemBinding.tvName.text = product.name
            itemBinding.tvId.text = product.id

            var price = product.price.toCurrency()
            product.maxPrice?.let {
                price += " ~ ${it.toCurrency()}"
            }
            itemBinding.tvPrice.text = price
        }
    }
}