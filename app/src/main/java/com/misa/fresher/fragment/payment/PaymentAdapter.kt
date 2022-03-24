package com.misa.fresher.fragment.payment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.ItemPackageRcvBinding
import com.misa.fresher.models.PackageProduct
import com.misa.fresher.showToast

class PaymentAdapter(
    private var listItemBillDetail: MutableList<PackageProduct>,
    private val listener: (itemBillDetail: PackageProduct) -> Unit
) :
    RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ItemPackageRcvBinding,
        val listener: (itemBillDetail: PackageProduct) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(itemBillDetail: PackageProduct) {
            binding.tvPriceAndType.text = itemBillDetail.getPrice().toString()
            binding.tvCountProduct.text = itemBillDetail.countPackage.toString()
            binding.tvNameProduct.text = itemBillDetail.nameProduct
            binding.tvCodeProduct.text = itemBillDetail.idProduct
            binding.tvPriceAndType.text =
                itemBillDetail.product.priceProduct.toString() + "/Package"
            binding.imgPackage.visibility = ViewGroup.GONE
            binding.imgAdd.setOnClickListener {
                itemBillDetail.countPackage += 1
                listener(itemBillDetail)
            }
            binding.imgRemove.setOnClickListener {
                if (itemBillDetail.countPackage > 1) {
                    itemBillDetail.countPackage -= 1
                } else {
                    binding.root.context.showToast("Quantity must be more than 0. Please check again")
                }
                listener(itemBillDetail)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPackageRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listItemBillDetail[position])

    override fun getItemCount() = listItemBillDetail.size
}


