package com.misa.fresher.ui.listbill.adapter.viewholder

import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.databinding.ItemBillBinding
import com.misa.fresher.util.toCurrency

/**
 * Viewholder cho item trong danh sách hóa đơn
 *
 * @author Nguyễn Công Chính
 * @since 3/16/2022
 *
 * @version 1
 * @updated 3/16/2022: Tạo class
 */
class BillViewHolder(
    private val binding: ItemBillBinding
) : BaseViewHolder<Bill>(binding.root) {

    override fun bindData(data: Bill, index: Int) {
        binding.tvId.text = data.id.toString()
        binding.tvCustomer.text = "${data.customer.name} - ${data.customer.tel}"
        binding.tvTotal.text = data.items.sumOf { it.item.price * it.quantity }.toCurrency()
    }
}