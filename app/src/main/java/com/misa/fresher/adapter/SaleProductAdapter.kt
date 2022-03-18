package com.misa.fresher.adapter

import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.customview.ItemSaleProductView
import com.misa.fresher.models.product.Product
import com.misa.fresher.utils.Enums
import com.misa.fresher.utils.showToast

class SaleProductAdapter(
    override var items: ArrayList<Product>,
    override var clickItemListener: (Product, Int) -> Unit,
    private val onAmountChanged: () -> Unit = {}
) : BaseAdapter<Product, BaseViewHolder<Product>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(ItemSaleProductView(parent.context), clickItemListener, onAmountChanged)

    class ProductViewHolder(
        private val itemSaleProductView: ItemSaleProductView,
        override var clickItemListener: (Product, Int) -> Unit,
        val onAmountChanged: () -> Unit
    ) : BaseViewHolder<Product>(itemSaleProductView) {

        override fun bindingData(item: Product, position: Int) {
            super.bindingData(item, position)
            itemSaleProductView.product = item
            itemSaleProductView.binding.run {
                btnMinus.setOnClickListener {
                    if(item.amount == 1) itemView.context.showToast("Quantity must be more than 0. Please check again.")
                    else {
                        txtAmount.text = (--item.amount).toString()
                        onAmountChanged()
                    }

                }
                btnAdd.setOnClickListener {
                    txtAmount.text = (++item.amount).toString()
                    onAmountChanged()
                }
            }
        }
    }
}


