package com.misa.fresher.ui.sale.bill.adapter.viewholder

import android.content.Context
import com.misa.fresher.R
import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.databinding.ItemProductInBillBinding
import com.misa.fresher.util.toCurrency
import com.misa.fresher.util.toast

/**
 * View holder cho mỗi sản phẩm trong màn hình thanh toán đơn hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 1
 * @updated 3/14/2022: Tạo class
 */
class ProductBillViewHolder(
    private val binding: ItemProductInBillBinding,
    private val context: Context,
    private val onUpdateTotalListener: () -> Unit
) : BaseViewHolder<ProductItemBill>(binding.root) {

    override fun bindData(data: ProductItemBill, index: Int) {
        binding.tvName.text = data.item.name
        binding.tvCode.text = data.item.code
        binding.tvPrice.text = "${data.item.price.toCurrency()}/${data.item.unit.name}"
        binding.tvQuantity.text = data.quantity.toString()
        binding.tvTotal.text = (data.item.price * data.quantity).toCurrency()

        binding.btnMinus.setOnClickListener {
            if (data.quantity <= 1) {
                toast(context, R.string.quantity_must_be_bigger_than_0)
            } else {
                data.quantity--
                binding.tvQuantity.text = data.quantity.toString()
                binding.tvTotal.text = (data.item.price * data.quantity).toCurrency()
                onUpdateTotalListener()
            }
        }
        binding.btnPlus.setOnClickListener {
            data.quantity++
            binding.tvQuantity.text = data.quantity.toString()
            binding.tvTotal.text = (data.item.price * data.quantity).toCurrency()
            onUpdateTotalListener()
        }
    }
}