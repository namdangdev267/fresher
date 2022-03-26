package com.misa.fresher.fragment.payment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.models.PackageProduct
import com.misa.fresher.databinding.ItemPackageRcvBinding
import com.misa.fresher.showToast
import java.util.*

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
        fun bind(packageProduct: PackageProduct) {
            binding.tvPriceProduct.text = packageProduct.getPrice().toString()
            binding.tvCountProduct.text = packageProduct.countPackage.toString()
            binding.tvNameProduct.text = packageProduct.namePackage
            binding.tvCodeProduct.text = packageProduct.codePackage
            binding.tvPriceProduct.text =
                packageProduct.product?.priceProduct.toString() + "/" + packageProduct.product?.category.toString()
                    .lowercase(Locale.getDefault())
            binding.imgPackage.visibility = ViewGroup.GONE
            binding.imgAdd.setOnClickListener {
                packageProduct.countPackage += 1
                listener(packageProduct)
            }
            binding.imgRemove.setOnClickListener {
                if (packageProduct.countPackage > 1) {
                    packageProduct.countPackage -= 1
                } else {
                    binding.root.context.showToast("Quantity must be more than 0. Please check again")
                }
                listener(packageProduct)
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


