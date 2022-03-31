package com.misa.fresher.ui.fragment.payment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.models.PackageProduct
import com.misa.fresher.databinding.ItemPackageRcvBinding
import com.misa.fresher.showToast
import java.util.*

class PaymentAdapter(
    var listItems: MutableList<PackageProduct>,
    private var clickItems: (PackageProduct) -> Unit
) : ListAdapter<PackageProduct, PaymentAdapter.PaymentViewHolder>(PostDiffCallBack()) {

    class PaymentViewHolder(
        private val binding: ItemPackageRcvBinding,
        private var clickItem: (PackageProduct) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindingData(item: PackageProduct) {
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
    ): PaymentViewHolder =
        PaymentViewHolder(
            ItemPackageRcvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), clickItems
        )

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bindingData(currentItem)
    }

}

class PostDiffCallBack : DiffUtil.ItemCallback<PackageProduct>() {
    override fun areItemsTheSame(oldItem: PackageProduct, newItem: PackageProduct): Boolean =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: PackageProduct, newItem: PackageProduct): Boolean =
        oldItem == newItem
}



