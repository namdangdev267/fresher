package com.misa.fresher.ui.billdetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.model.SelectedProducts
import com.misa.fresher.databinding.ItemBillDetailBinding
import com.misa.fresher.showToast

/**
 * Tạo Adapter dùng cho RecyclerView trong màn BillDetail
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class ProductSelectedAdapter(
        var mSelectedProducts: MutableList<SelectedProducts>,
        val updateTotalPrice: (products: SelectedProducts) -> Unit
) :
        RecyclerView.Adapter<ProductSelectedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ViewHolder {
        val binding = ItemBillDetailBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selected = mSelectedProducts[position]
        with(holder) {
            tvProductId.text = selected.product.code
            tvProductName.text = selected.product.name
            tvProductPrice.text = selected.product.price.toString()
            tvAmount.text = selected.amonut.toString()
            tvTotalPrice.text = "${selected.let { it.product.price * it.amonut }}"
        }
        holder.btnAdd.setOnClickListener {
            selected.amonut++
            updateTotalPrice(selected)
        }
        holder.btnRemove.setOnClickListener {
            if (mSelectedProducts[position].amonut > 1) {
                selected.amonut--
                updateTotalPrice(selected)
            } else {
                holder.contextVH.showToast("Số lượng phải lớn hơn 0. Vui lòng kiểm tra lại")
            }
        }
    }

    override fun getItemCount() = mSelectedProducts.size

    class ViewHolder(val binding: ItemBillDetailBinding, val contextVH: Context) :
            RecyclerView.ViewHolder(binding.root) {
        val tvProductId = binding.tvProductId
        val tvProductName = binding.tvProductName
        val tvProductPrice = binding.tvProductPrice
        val tvAmount = binding.tvProductAmont
        val tvTotalPrice = binding.tvTotalPrice
        val btnAdd = binding.ivAdd
        val btnRemove = binding.ivRemove
    }
}