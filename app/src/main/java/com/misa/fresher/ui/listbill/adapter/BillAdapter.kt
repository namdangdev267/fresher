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
 * @version 1
 * @updated 3/16/2022: Tạo class
 */
class BillAdapter(
    items: MutableList<Bill>
) : BaseRecyclerAdapter<Bill, BillViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder =
        BillViewHolder(
            ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    /**
     * Hàm cập nhật toàn bộ danh sách hóa đơn
     *
     * @author Nguyễn Công Chính
     * @since 3/16/2022
     *
     * @version 1
     * @updated 3/16/2022: Tạo function
     */
    fun updateProductList(list: List<Bill>) {
        val tempSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, tempSize)
        items.addAll(list)
        notifyItemRangeInserted(0, items.size)
    }
}