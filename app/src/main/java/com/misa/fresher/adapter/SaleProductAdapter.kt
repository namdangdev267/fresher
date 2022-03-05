package com.misa.fresher.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.databinding.ItemSaleProductBinding
import com.misa.fresher.models.Product

class SaleProductAdapter(
    override var items: ArrayList<Product>,
    override var clickItemListener: (Product) -> Unit
) : BaseAdapter<Product, SaleProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemSaleProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        clickItemListener
    )

    class ProductViewHolder(
        private val binding: ItemSaleProductBinding,
        override var clickItemListener: (Product) -> Unit
    ) : BaseViewHolder<Product>(binding.root) {

        override fun bindingData(item: Product) {
            super.bindingData(item)

            binding.apply {
                productCode.text = item.code
                productName.text = item.name
                productPrice.text = item.price.toString()
            }
        }
    }
}


