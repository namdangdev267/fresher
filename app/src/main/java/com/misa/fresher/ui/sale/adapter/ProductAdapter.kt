package com.misa.fresher.ui.sale.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.core.BaseRecyclerAdapter
import com.misa.fresher.data.entity.Product
import com.misa.fresher.databinding.ItemProductBinding
import com.misa.fresher.ui.sale.adapter.viewholder.ProductViewHolder

/**
 * Adapter cho danh sách sản phẩm ở màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 3
 * @updated 3/10/2022: Tạo class
 * @updated 3/12/2022: Sửa tham số truyền vào hàm [updateProductList]
 * @updated 3/18/2022: Override hàm [areContentsTheSame], [areItemsTheSame] tương ứng với lớp cha, không sử dụng hàm updateProductList nữa, dùng updateData thay thế
 */
class ProductAdapter(
    items: MutableList<Product>,
    private val onItemClickListener: (Product) -> Unit
) : BaseRecyclerAdapter<Product, ProductViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.equals(newItem)

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id
}