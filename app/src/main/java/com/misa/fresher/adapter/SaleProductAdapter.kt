package com.misa.fresher.adapter

import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.customview.ItemSaleProductView
import com.misa.fresher.databinding.ItemSaleProductBinding
import com.misa.fresher.models.product.Product
import com.misa.fresher.utils.Enums

class SaleProductAdapter(
    override var items: ArrayList<Product>, override var clickItemListener: (Product, Int) -> Unit
) : BaseAdapter<Product, BaseViewHolder<Product>>() {

    override fun getItemViewType(position: Int): Int {
        if (items[position].items.size == 1) {
            return if (items[position].isUnitSelect) Enums.Product.ITEM_SELECTED.ordinal
            else Enums.Product.ITEM.ordinal
        }
        return Enums.Product.MODEL.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Product> {
        val view = ItemSaleProductView(parent.context, null)
        return when (viewType) {
            Enums.Product.ITEM.ordinal -> {
                ProductItemViewHolder(view, clickItemListener)
            }
            Enums.Product.MODEL.ordinal -> {
                ProductModelViewHolder(view, clickItemListener)
            }
            else -> {
                ProductItemSelectedViewHolder(view, clickItemListener)
            }
        }
    }

    class ProductModelViewHolder(
        val itemSaleProductView: ItemSaleProductView,
        override var clickItemListener: (Product, Int) -> Unit
    ) : BaseViewHolder<Product>(itemSaleProductView) {
        override fun bindingData(item: Product, position: Int) {
            itemSaleProductView.apply {
                image = item.image
                name = item.name
                code = item.code
                price = item.getPrice()
                unit = item.unit
                amount = 0
                isUnitSelect = item.isUnitSelect
            }
            itemSaleProductView.binding.root.setOnClickListener {
                clickItemListener(item, position)
            }
        }
    }

    class ProductItemViewHolder(
        val itemSaleProductView: ItemSaleProductView, override var clickItemListener: (Product, Int) -> Unit
    ) : BaseViewHolder<Product>(itemSaleProductView) {

        override fun bindingData(product: Product, position: Int) {
            val productItem = product.items[0]
            itemSaleProductView.apply {
                image = product.image
                name = "${product.name } (${productItem.color}/${productItem.size})"
                code = product.code
                price = productItem.price.toString()
                unit = product.unit
                amount = productItem.amount
                isUnitSelect = product.isUnitSelect
            }
            itemSaleProductView.binding.root.setOnClickListener {
                clickItemListener(product, position)
            }
        }
    }

    class ProductItemSelectedViewHolder(
        val itemSaleProductView: ItemSaleProductView, override var clickItemListener: (Product, Int) -> Unit
    ) : BaseViewHolder<Product>(itemSaleProductView) {
        override fun bindingData(item: Product, position: Int) {
            itemSaleProductView.apply {
                image = 0
                name = item.name
                code = item.code
                price = item.getPrice()
                unit = item.unit
                amount = item.getAmount()
                isUnitSelect = item.isUnitSelect
            }
            itemSaleProductView.binding.root.setOnClickListener {
                clickItemListener(item, position)
            }
        }
    }
}


