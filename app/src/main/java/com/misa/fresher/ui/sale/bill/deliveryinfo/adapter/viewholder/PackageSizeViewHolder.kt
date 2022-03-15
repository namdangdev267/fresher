package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.data.model.PackageSizeInputModel
import com.misa.fresher.databinding.ItemPackageSizeBinding

/**
 * Viewholder cho item nhập kích thước gói hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class PackageSizeViewHolder(
    private val binding: ItemPackageSizeBinding
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        data as PackageSizeInputModel
        binding.tvHeader.text = data.title
        binding.tvRequired.text = if (data.isRequired) "*" else ""
    }
}