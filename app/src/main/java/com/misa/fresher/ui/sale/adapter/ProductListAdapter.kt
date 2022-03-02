package com.misa.fresher.ui.sale.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.core.BaseViewHolder
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
    ) : BaseViewHolder<Product>(itemBinding.root) {

        override fun bindData(data: Product) {
            itemBinding.root.setOnClickListener {
                onClickListener(data)
            }

            itemBinding.tvName.text = data.name
            itemBinding.tvId.text = data.id

            var price = data.price.toCurrency()
            data.maxPrice?.let {
                price += " ~ ${it.toCurrency()}"
            }
            itemBinding.tvPrice.text = price
        }
    }
}