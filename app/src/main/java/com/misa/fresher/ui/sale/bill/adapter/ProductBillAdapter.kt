package com.misa.fresher.ui.sale.bill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.core.BaseRecyclerAdapter
import com.misa.fresher.data.model.CartItemModel
import com.misa.fresher.databinding.ItemProductInBillBinding
import com.misa.fresher.ui.sale.bill.adapter.viewholder.ProductBillViewHolder

/**
 * Adapter dành cho các sản phẩm ở màn hình
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 1
 * @updated 3/14/2022: Tạo class
 */
class ProductBillAdapter(
    items: MutableList<CartItemModel>,
    private val context: Context,
    private val onUpdateTotalListener: () -> Unit
) : BaseRecyclerAdapter<CartItemModel, ProductBillViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductBillViewHolder =
        ProductBillViewHolder(ItemProductInBillBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), context, onUpdateTotalListener)

    /**
     * Hàm cập nhật toàn bộ danh sách sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/14/2022
     *
     * @version 1
     * @updated 3/14/2022: Tạo function
     */
    fun updateProductList(list: List<CartItemModel>) {
        val tempSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, tempSize)
        items.addAll(list)
        notifyItemRangeInserted(0, items.size)
    }
}