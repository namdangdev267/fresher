package com.misa.fresher.ui.sale.adapter.viewholder

import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.entity.ProductType
import com.misa.fresher.databinding.ItemTypeSelectorBinding

/**
 * View holder của mỗi lựa chọn trong hộp thoại chọn loại sản phẩm
 *
 * @author Nguyễn Công Chính
 * @since 3/11/2022
 *
 * @version 1
 * @updated 3/11/2022: Tạo class
 */
class TypeSelectorViewHolder<T : ProductType>(
    private val binding: ItemTypeSelectorBinding,
    private val onCheckedChangeListener: (data: T) -> Unit,
) : BaseViewHolder<T>(binding.root) {

    override fun bindData(data: T) {
        binding.root.text = data.name
        binding.root.setOnCheckedChangeListener { _, b ->
            if (b) {
                onCheckedChangeListener(data)
            }
        }
    }

    /**
     * Hàm cài đặt chọn hay không chọn cho mỗi phẩn tử trong danh sách
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 1
     * @updated 3/11/2022: Tạo function
     */
    fun setChecked(isChecked: Boolean) {
        binding.root.isChecked = isChecked
    }
}