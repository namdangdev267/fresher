package com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.viewholder

import android.view.ViewGroup
import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.model.InputInfoModel
import com.misa.fresher.data.model.TwoColumnInputModel
import com.misa.fresher.databinding.ItemTwoColumnInputBinding
import com.misa.fresher.ui.sale.bill.deliveryinfo.adapter.DeliveryInputAdapter

/**
 * Viewholder cho item có 2 cột
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo class
 */
class TwoColumnViewHolder(
    private val binding: ItemTwoColumnInputBinding,
    private val adapter: DeliveryInputAdapter,
    private val parent: ViewGroup
) : BaseViewHolder<InputInfoModel>(binding.root) {

    override fun bindData(data: InputInfoModel, index: Int) {
        data as TwoColumnInputModel
        val col0Holder = adapter.onCreateViewHolder(parent, data.column0.type.ordinal)
        col0Holder.bindData(data.column0, 0)
        binding.flCol0.addView(col0Holder.itemView)

        val col1Holder = adapter.onCreateViewHolder(parent, data.column1.type.ordinal)
        col1Holder.bindData(data.column1, 1)
        binding.flCol1.addView(col1Holder.itemView)
    }
}