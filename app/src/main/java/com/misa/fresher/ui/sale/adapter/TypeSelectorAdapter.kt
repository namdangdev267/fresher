package com.misa.fresher.ui.sale.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.core.BaseRecyclerAdapter
import com.misa.fresher.data.entity.ProductType
import com.misa.fresher.databinding.ItemTypeSelectorBinding
import com.misa.fresher.ui.sale.adapter.viewholder.TypeSelectorViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Adapter cho các recyclerview trong hộp thoại chọn loại sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/11/2022
 *
 * @version 1
 * @updated 3/11/2022: Tạo class
 */
class TypeSelectorAdapter<T : ProductType>(
    items: MutableList<T>,
    private val onItemSelectedListener: (T) -> Unit
) : BaseRecyclerAdapter<T, TypeSelectorViewHolder<T>>(items) {

    private var checkedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeSelectorViewHolder<T> =
        TypeSelectorViewHolder(
            ItemTypeSelectorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) { data ->
            val temp = checkedPos
            checkedPos = items.indexOf(data)
            onItemSelectedListener(data)

            // Sở dĩ phải sử dụng coroutine là do hàm này được gọi khi recyclerview đang tính toán
            // kích thước cho các phần tử (theo lỗi báo từ ide).
            CoroutineScope(Dispatchers.Main).launch {
                if (temp >= 0) {
                    notifyItemChanged(temp)
                }
                notifyItemChanged(checkedPos)
            }
        }

    override fun onBindViewHolder(holder: TypeSelectorViewHolder<T>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.setChecked(checkedPos == position)
    }
}