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
 * @version 1
 * @updated 3/15/2022: Tạo class
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
            binding.ivIcon.setImageDrawable(it)
        }
        binding.tvInput.text = data.input
    }
}