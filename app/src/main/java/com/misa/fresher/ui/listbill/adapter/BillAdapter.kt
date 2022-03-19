package com.misa.fresher.ui.listbill.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.core.BaseRecyclerAdapter
import com.misa.fresher.data.entity.Bill
import com.misa.fresher.databinding.ItemBillBinding
import com.misa.fresher.ui.listbill.adapter.viewholder.BillViewHolder

/**
 * Adapter danh sách hóa đơn cho màn danh sách hóa đơn
 *
 * @author Nguyễn Công Chính
 * @since 3/16/2022
 *
 * @version 2
 * @updated 3/16/2022: Tạo class
 * @updated 3/18/2022: Override hàm [areContentsTheSame], [areItemsTheSame] tương ứng với lớp cha, loại bỏ hàm updateProductList, chuyển qua dùng updateData
 */
class BillAdapter(
    items: MutableList<Bill>
) : BaseRecyclerAdapter<Bill, BillViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder =
        BillViewHolder(
            ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun areContentsTheSame(oldItem: Bill, newItem: Bill): Boolean =
        oldItem.equals(newItem)

    override fun areItemsTheSame(oldItem: Bill, newItem: Bill): Boolean =
        oldItem.id == newItem.id
}