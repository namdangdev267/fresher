package com.misa.fresher.ui.fragment.sale

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.data.models.Product
import com.misa.fresher.databinding.ItemPackageRcvBinding


//class ProductAdapter(
//    override var listItems: MutableList<Product>,
//    override var clickItems: (Product) -> Unit
//) : BaseAdapter<Product, BaseViewHolder<Product>>() {
//
//
//    class ProductViewholder(
//        val binding: ItemPackageRcvBinding,
//        override var clickItem: (Product) -> Unit
//    ) :
//        BaseViewHolder<Product>(binding.root) {
//        override fun bindingData(item: Product) {
//            super.bindingData(item)
//            binding.run {
//                imgAdd.visibility = View.GONE
//                imgRemove.visibility = View.GONE
//                tvCountProduct.visibility = View.GONE
//                tvCountProduct.visibility = View.GONE
//                tvNameProduct.text = item.nameProduct
//                tvCodeProduct.text = item.codeProduct
//                tvPriceProduct.text = item.priceProduct.toString()
//                imgPackage.setImageResource(item.imgProduct)
//                root.setOnClickListener {
//                    clickItem(item)
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Product> =
//        ProductViewholder(
//            ItemPackageRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false),
//            clickItems
//        )
//
//}
//}

class ProductAdapter(
    var listItems: MutableList<Product>,
    private var clickItems: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(PostDiffCallBack()) {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    class ProductViewHolder(
        private val binding: ItemPackageRcvBinding,
        var clickItem: (Product) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindingData(product: Product) {
            binding.apply {
                imgAdd.visibility = View.GONE
                imgRemove.visibility = View.GONE
                tvCountProduct.visibility = View.GONE
                tvCountProduct.visibility = View.GONE
                tvNameProduct.text = product.nameProduct
                tvCodeProduct.text = product.codeProduct
                tvPriceProduct.text = product.priceProduct.toString()
                imgPackage.setImageResource(product.imgProduct)
                root.setOnClickListener {
                    clickItem(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder = ProductViewHolder(
        ItemPackageRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        clickItems
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bindingData(currentItem)
    }
}

class PostDiffCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem
}
