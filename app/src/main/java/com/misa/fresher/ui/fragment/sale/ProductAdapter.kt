package com.misa.fresher.ui.fragment.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.data.models.Product
import com.misa.fresher.databinding.ItemPackageRcvBinding

class ProductAdapter(
    override var listItems: MutableList<Product>,
    override var clickItems: (Product) -> Unit
) : BaseAdapter<Product, BaseViewHolder<Product>>() {


    class ProductViewholder(
        val binding: ItemPackageRcvBinding,
        override var clickItem: (Product) -> Unit
    ) :
        BaseViewHolder<Product>(binding.root) {
        override fun bindingData(item: Product) {
            super.bindingData(item)
            binding.run {
                imgAdd.visibility = View.GONE
                imgRemove.visibility = View.GONE
                tvCountProduct.visibility = View.GONE
                tvCountProduct.visibility = View.GONE
                tvNameProduct.text = item.nameProduct
                tvCodeProduct.text = item.codeProduct
                tvPriceProduct.text = item.priceProduct.toString()
                imgPackage.setImageResource(item.imgProduct)
                root.setOnClickListener {
                    clickItem(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Product> =
        ProductViewholder(
            ItemPackageRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickItems
        )

}
