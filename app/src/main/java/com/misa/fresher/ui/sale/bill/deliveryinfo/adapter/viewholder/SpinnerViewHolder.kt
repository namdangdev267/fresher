package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import android.content.Context
import android.widget.ArrayAdapter
import com.misa.fresher.R
import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.data.model.SpinnerInputModel
import com.misa.fresher.data.model.TapInsertInputModel
import com.misa.fresher.databinding.ItemSpinnerInputBinding

/**
 * Viewholder của trường nhập loại dùng spinner
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class SpinnerViewHolder(
    private val binding: ItemSpinnerInputBinding,
    private val context: Context
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        data as SpinnerInputModel
        binding.tvHeader.text = data.title
        binding.tvRequired.text = if (data.isRequired) "*" else ""
        binding.spnInput.adapter = ArrayAdapter(context, R.layout.item_simple_text_spinner, data.items)
    }
}