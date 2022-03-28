package com.misa.fresher.ui.fragment.payment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.misa.fresher.base.BaseAdapter
import com.misa.fresher.base.BaseViewHolder
import com.misa.fresher.data.models.PackageProduct
import com.misa.fresher.databinding.ItemPackageRcvBinding
import com.misa.fresher.showToast
import java.util.*

class PaymentAdapter(
    override var listItems: MutableList<PackageProduct>,
    override var clickItems: (PackageProduct) -> Unit
) : BaseAdapter<PackageProduct, BaseViewHolder<PackageProduct>>() {

    class PaymentViewholder(
        val binding: ItemPackageRcvBinding,
        override var clickItem: (PackageProduct) -> Unit
    ) : BaseViewHolder<PackageProduct>(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bindingData(item: PackageProduct) {
            super.bindingData(item)
            binding.run {
                tvPriceProduct.text = item.getPrice().toString()
                tvCountProduct.text = item.countPackage.toString()
                tvNameProduct.text = item.namePackage
                tvCodeProduct.text = item.codePackage
                tvPriceProduct.text =
                    item.product?.priceProduct.toString() + "/" + item.product?.category.toString()
                        .lowercase(Locale.getDefault())
                imgPackage.visibility = ViewGroup.GONE
                imgAdd.setOnClickListener {
                    item.countPackage += 1
                    clickItem(item)
                }
                imgRemove.setOnClickListener {
                    if (item.countPackage > 1) {
                        item.countPackage -= 1
                    } else {
                        root.context.showToast("Quantity must be more than 0. Please check again")
                    }
                    clickItem(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<PackageProduct> =
        PaymentViewholder(
            ItemPackageRcvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), clickItems
        )

}


