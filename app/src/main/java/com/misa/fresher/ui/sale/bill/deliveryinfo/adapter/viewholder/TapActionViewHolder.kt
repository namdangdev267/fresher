package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.data.model.TapActionInputModel
import com.misa.fresher.databinding.ItemTapActionInputBinding

/**
 * Viewholder của trường nhập loại chạm để chọn (tap to action)
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 2
 * @updated 3/15/2022: Tạo class
 * @updated 3/16/2022: Đổi từ 1 textview + imageview -> 1 textview với drawable
 */
class TapActionViewHolder(
    private val binding: ItemTapActionInputBinding
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        data as TapActionInputModel
        binding.root.setOnClickListener {
            data.onClickListener()
        }
        binding.tvHeader.text = data.title
        binding.tvRequired.text = if (data.isRequired) "*" else ""
        data.icon?.let {
            it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            binding.tvInput.setCompoundDrawablesRelative(null, null, it, null)
        }
        binding.tvInput.text = data.input
    }
}