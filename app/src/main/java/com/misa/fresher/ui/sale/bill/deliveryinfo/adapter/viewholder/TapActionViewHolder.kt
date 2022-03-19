package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import android.content.Context
import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.data.model.TapActionInputModel
import com.misa.fresher.databinding.ItemTapActionInputBinding
import com.misa.fresher.util.getDrawableById

/**
 * Viewholder của trường nhập loại chạm để chọn (tap to action)
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 4
 * @updated 3/15/2022: Tạo class
 * @updated 3/16/2022: Đổi từ 1 textview + imageview -> 1 textview với drawable
 * @updated 3/16/2022: Thêm hàm lấy dữ liệu đã chọn
 * @updated 3/18/2022: Trao đổi dữ liệu thông qua trường input, loại bỏ hàm collectData
 */
class TapActionViewHolder(
    private val binding: ItemTapActionInputBinding,
    private val context: Context
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        data as TapActionInputModel
        binding.root.setOnClickListener {
            data.onClickListener()
        }
        binding.tvHeader.text = data.title
        binding.tvRequired.text = if (data.isRequired) "*" else ""
        data.icon?.let { id ->
            context.resources.getDrawableById(id)?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
                binding.tvInput.setCompoundDrawablesRelative(null, null, it, null)
            }
        }
        data.input?.let {
            binding.tvInput.text = it as String
        } ?: run {
            binding.tvInput.text = ""
        }
    }
}