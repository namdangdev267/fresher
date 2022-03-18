package com.misa.fresher.ui.sale.adapter

import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.customview.ViewProduct
import com.misa.fresher.data.model.product.Product
import com.misa.fresher.utils.showToast

class SaleProductAdapter(
    override var items: ArrayList<Product>,
    override var clickItemListener: (Product, Int) -> Unit,
    private val onAmountChanged: () -> Unit = {}
) : BaseAdapter<Product, BaseViewHolder<Product>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder(ViewProduct(parent.context), clickItemListener, onAmountChanged)

    class ProductViewHolder(
        private val viewProduct: ViewProduct,
        override var clickItemListener: (Product, Int) -> Unit,
        val onAmountChanged: () -> Unit
    ) : BaseViewHolder<Product>(viewProduct) {

        override fun bindingData(item: Product, position: Int) {
            super.bindingData(item, position)
            viewProduct.product = item
            viewProduct.binding.btnMinus.setOnClickListener {
                if (viewProduct.amount == 1) itemView.context.showToast("Quantity must be more than 0. Please check again.")
                else {
                    viewProduct.amount--
                    onAmountChanged()
                }

            }
            viewProduct.binding.btnAdd.setOnClickListener {
                viewProduct.amount++
                onAmountChanged()
            }
        }
    }
}


