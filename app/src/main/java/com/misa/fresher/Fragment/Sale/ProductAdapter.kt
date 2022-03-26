package com.misa.fresher.fragment.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.models.Product
import com.misa.fresher.databinding.ItemPackageRcvBinding

class ProductAdapter(
    private val productList: MutableList<Product>,
    val clickProductListener: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemPackageRcvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemProduct: Product) {
            binding.run {
                imgAdd.visibility = View.GONE
                imgRemove.visibility = View.GONE
                tvCountProduct.visibility = View.GONE
                tvCountProduct.visibility = View.GONE
                tvNameProduct.text = itemProduct.nameProduct
                tvCodeProduct.text = itemProduct.codeProduct
                tvPriceProduct.text = itemProduct.priceProduct.toString()
                imgPackage.setImageResource(itemProduct.imgProduct)
                root.setOnClickListener {
                    clickProductListener(itemProduct)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemPackageRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(productList[position])

    override fun getItemCount(): Int = productList.size
}
