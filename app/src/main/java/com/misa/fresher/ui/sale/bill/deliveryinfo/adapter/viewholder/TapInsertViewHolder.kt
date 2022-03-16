package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.data.model.TapInsertInputModel
import com.misa.fresher.databinding.ItemTapInsertInputBinding

/**
 * Viewholder của trường nhập loại chạm để nhập (tap to insert)
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 2
 * @updated 3/15/2022: Tạo class
 * @updated 3/16/2022: Thêm hàm lấy dữ liệu đã nhập
 */
class TapInsertViewHolder(
    private val binding: ItemTapInsertInputBinding
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        data as TapInsertInputModel
        binding.tvHeader.text = data.title
        binding.tvRequired.text = if (data.isRequired) "*" else ""
        data.icon?.let {
            binding.ivIcon.setImageDrawable(it)
        }
        binding.etInput.inputType = data.inputType
        binding.etInput.setText(data.input)
    }

    /**
     * Hàm lấy dữ liệu đã nhập
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 1
     * @updated 3/16/2022: Tạo function
     */
    fun collectData(): String = binding.etInput.text.toString()
}