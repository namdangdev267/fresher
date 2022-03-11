package com.misa.fresher.ui.sale.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.core.BaseRecyclerAdapter
import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.entity.ProductItem
import com.misa.fresher.databinding.ItemProductBinding
import com.misa.fresher.ui.sale.adapter.viewholder.ProductViewHolder

/**
 * Adapter cho danh sách sản phẩm ở màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 1
 * @updated 3/10/2022: Tạo class
 */
class ProductAdapter(
    items: MutableList<Product>,
    private val context: Context,
    private val onProductItemSelectedListener: (item: ProductItem, quantity: Int) -> Unit
) : BaseRecyclerAdapter<Product, ProductViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context,
            onProductItemSelectedListener
        )

    /**
     * Cập nhật toàn bộ danh sách sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    fun updateProductList(list: MutableList<Product>) {
        val tempSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, tempSize)
        items.addAll(list)
        notifyItemRangeInserted(0, items.size)
    }
}