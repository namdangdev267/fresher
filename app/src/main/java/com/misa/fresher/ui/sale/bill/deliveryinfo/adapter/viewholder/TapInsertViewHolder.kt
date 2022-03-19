package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import androidx.core.widget.addTextChangedListener
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
 * @version 3
 * @updated 3/15/2022: Tạo class
 * @updated 3/16/2022: Thêm hàm lấy dữ liệu đã nhập
 * @updated 3/18/2022: Trao đổi dữ liệu thông qua trường input, loại bỏ hàm collectData
 */
class TapInsertViewHolder(
    private val binding: ItemTapInsertInputBinding
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        data as TapInsertInputModel
        binding.tvHeader.text = data.title
        binding.tvRequired.text = if (data.isRequired) "*" else ""
        data.icon?.let {
            binding.ivIcon.setImageResource(it)
        }
        binding.etInput.inputType = data.inputType
        data.input?.let {
            binding.etInput.setText(it as String)
        } ?: run {
            binding.etInput.setText("")
        }
        binding.etInput.addTextChangedListener { editable ->
            editable?.let {
                data.input = if (it.isEmpty()) null else it.toString()
            }
        }
    }
}