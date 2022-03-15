package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.databinding.ItemDeliveryTypeBinding

/**
 * Viewholder cho item chọn kiểu giao hàng (tổ chức/cá nhân)
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class DeliveryTypeViewHolder(
    private val binding: ItemDeliveryTypeBinding
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        binding.rbOrganization.isChecked = true
    }
}