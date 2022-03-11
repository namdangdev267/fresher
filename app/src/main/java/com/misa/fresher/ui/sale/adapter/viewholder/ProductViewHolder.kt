package com.misa.fresher.ui.sale.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.R
import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.entity.ProductItem
import com.misa.fresher.databinding.DialogProductTypeSelectBinding
import com.misa.fresher.databinding.ItemProductBinding
import com.misa.fresher.util.toCurrency

/**
 * View holder của mỗi sản phẩm trong màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 1
 * @updated 3/10/2022: Tạo class
 */
class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val context: Context,
    private val onProductItemSelectedListener: (item: ProductItem, quantity: Int) -> Unit
) :
    BaseViewHolder<Product>(binding.root) {

    override fun bindData(data: Product) {
        binding.tvName.text = data.name
        binding.tvCode.text = data.code

        val minPrice = data.items.minOf { it.price }
        val maxPrice = data.items.maxOf { it.price }
        binding.tvPrice.text = if (minPrice == maxPrice) {
            minPrice.toCurrency()
        } else {
            "${minPrice.toCurrency()} ~ ${maxPrice.toCurrency()}"
        }
    }
}