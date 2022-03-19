package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.data.model.SingleCheckBoxInputModel
import com.misa.fresher.databinding.ItemSingleCheckboxBinding

/**
 * View holder cho item có 1 check box duy nhất
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class SingleCheckBoxViewHolder(
    private val binding: ItemSingleCheckboxBinding
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        data as SingleCheckBoxInputModel
        binding.cbInput.text = data.title
    }
}