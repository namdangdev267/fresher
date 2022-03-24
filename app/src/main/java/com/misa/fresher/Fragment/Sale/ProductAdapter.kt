package com.misa.fresher.fragment.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.databinding.ItemPackageRcvBinding
import com.misa.fresher.models.Product

open class ProductAdapter(
    private val productList: MutableList<Product>,
    private val onClickItemListener: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    class MyViewHolder(
        private val binding: ItemPackageRcvBinding,
        val listener: (itemProduct: Product) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemProduct: Product) {

            binding.imgAdd.visibility = View.GONE
            binding.imgRemove.visibility = View.GONE
            binding.tvCountProduct.visibility = View.GONE
            binding.tvCountProduct.visibility = View.GONE

            binding.tvNameProduct.text = itemProduct.nameProduct
            binding.tvCodeProduct.text = itemProduct.codeProduct
            binding.tvPriceProduct.text = itemProduct.priceProduct.toString()
            binding.imgPackage.setImageResource(itemProduct.imgProduct)
            binding.root.setOnClickListener {
                listener(itemProduct)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemPackageRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onClickItemListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(productList[position])

    override fun getItemCount(): Int = productList.size
}
