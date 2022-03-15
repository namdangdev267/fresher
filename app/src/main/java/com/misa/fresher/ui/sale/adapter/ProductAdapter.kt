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
 * @version 2
 * @updated 3/10/2022: Tạo class
 * @updated 3/12/2022: Sửa tham số truyền vào hàm [updateProductList]
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

    /**
     * Hàm cập nhật toàn bộ danh sách sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 2
     * @updated 3/10/2022: Tạo function
     * @updated 3/12/2022: [list] truyền vào không có nhu cầu thay đổi, do vậy chuyển từ mutablelist -> list
     */
    fun updateProductList(list: List<Product>) {
        val tempSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, tempSize)
        items.addAll(list)
        notifyItemRangeInserted(0, items.size)
    }
}