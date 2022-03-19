package com.misa.fresher.ui.listbills.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.databinding.ItemBillBinding

class ListBillAdapter(
    override var items: ArrayList<ProductBill>, override var clickItemListener: (ProductBill, Int) -> Unit
) : BaseAdapter<ProductBill, BaseViewHolder<ProductBill>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder =
        BillViewHolder(ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent, false), clickItemListener)

    class BillViewHolder(
        private val binding: ItemBillBinding, override var clickItemListener: (ProductBill, Int) -> Unit
    ) : BaseViewHolder<ProductBill>(binding.root) {
        override fun bindingData(item: ProductBill, position: Int) {
            super.bindingData(item, position)
            binding.txtBillId.text = item.id.toString()
            binding.txtBillPrice.text = item.price.toString()
        }
    }
}
