package com.misa.fresher.ui.billdetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.ItemBillDetailBinding
import com.misa.fresher.data.model.SelectedProducts
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
        holder.tvProductId.text = mSelectedProducts[position].product.code
        holder.tvProductName.text = mSelectedProducts[position].product.name
        holder.tvProductPrice.text = mSelectedProducts[position].product.price.toString()
        holder.tvAmount.text = mSelectedProducts[position].amonut.toString()
        holder.tvTotalPrice.text =
            "${mSelectedProducts[position].let { it.product.price * it.amonut }}"
        holder.binding.ivAdd.setOnClickListener {
            mSelectedProducts[position].amonut++
            updateTotalPrice(mSelectedProducts[position])
        }
        holder.binding.ivRemove.setOnClickListener {
            if (mSelectedProducts[position].amonut > 1) {
                mSelectedProducts[position].amonut--
                updateTotalPrice(mSelectedProducts[position])
            } else {
                holder.contextVH.showToast("Số lượng phải lớn hơn 0. Vui lòng kiểm tra lại")
            }
        }
    }

    override fun getItemCount() = mSelectedProducts.size

    inner class ViewHolder(val binding: ItemBillDetailBinding, val contextVH: Context) :
        RecyclerView.ViewHolder(binding.root) {
        val tvProductId = binding.tvProductId
        val tvProductName = binding.tvProductName
        val tvProductPrice = binding.tvProductPrice
        val tvAmount = binding.tvProductAmont
        val tvTotalPrice = binding.tvTotalPrice
    }


}