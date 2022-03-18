package com.misa.fresher.ui.sale.bill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.core.BaseRecyclerAdapter
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.databinding.ItemProductInBillBinding
import com.misa.fresher.ui.sale.bill.adapter.viewholder.ProductBillViewHolder

/**
 * Adapter dành cho các sản phẩm ở màn hình
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 2
 * @updated 3/14/2022: Tạo class
 * @updated 3/18/2022: Override hàm [areContentsTheSame], [areItemsTheSame] tương ứng với lớp cha, không sử dụng hàm updateProductList nữa, dùng updateData thay thế
 */
class ProductBillAdapter(
    items: MutableList<ProductItemBill>,
    private val context: Context,
    private val onUpdateTotalListener: () -> Unit
) : BaseRecyclerAdapter<ProductItemBill, ProductBillViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductBillViewHolder =
        ProductBillViewHolder(ItemProductInBillBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), context, onUpdateTotalListener)

    override fun areContentsTheSame(oldItem: ProductItemBill, newItem: ProductItemBill): Boolean =
        oldItem.equals(newItem)

    override fun areItemsTheSame(oldItem: ProductItemBill, newItem: ProductItemBill): Boolean =
        oldItem.item.id == newItem.item.id
}