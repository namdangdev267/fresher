package com.misa.fresher.ui.sale.adapter.viewholder

import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.entity.*
import com.misa.fresher.databinding.ItemProductBinding
import com.misa.fresher.util.toCurrency

/**
 * View holder của mỗi sản phẩm trong màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 2
 * @updated 3/10/2022: Tạo class
 * @updated 3/12/2022: Cài dialog hiện ra khi nhấn vào item
 * @updated 3/15/2022: Chuyển nhà cho dialog, thay vì mỗi [ProductViewHolder] nắm 1 dialog, [com.misa.fresher.ui.sale.SaleFragment] sẽ nắm 1 dialog duy nhất
 */
class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onItemClickListener: (Product) -> Unit,
) : BaseViewHolder<Product>(binding.root) {

    override fun bindData(data: Product, index: Int) {
        binding.root.setOnClickListener {
            onItemClickListener(data)
        }

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