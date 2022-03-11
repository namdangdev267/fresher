package com.misa.fresher.ui.sale.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.R
import com.misa.fresher.core.BaseViewHolder
import com.misa.fresher.data.entity.*
import com.misa.fresher.databinding.DialogProductTypeSelectorBinding
import com.misa.fresher.databinding.ItemProductBinding
import com.misa.fresher.ui.sale.adapter.TypeSelectorAdapter
import com.misa.fresher.util.guard
import com.misa.fresher.util.toCurrency

/**
 * View holder của mỗi sản phẩm trong màn hình bán hàng
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 2
 * @updated 3/10/2022: Tạo class
 * @updated 3/12/2022: Cài dialog hiện ra khi nhấn vào item
 */
class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val context: Context,
    private val onProductItemSelectedListener: (item: ProductItem, quantity: Int) -> Unit
) : BaseViewHolder<Product>(binding.root) {

    private var dialog: BottomSheetDialog? = null
    private val dialogBinding = DialogProductTypeSelectorBinding.inflate(LayoutInflater.from(context))

    private var quantity = 1
    private var color: ProductColor? = null
    private var size: ProductSize? = null
    private var unit: ProductUnit? = null

    override fun bindData(data: Product) {
        binding.root.setOnClickListener {
            showProductTypeSelectDialog(data)
        }

        binding.tvName.text = data.name
        binding.tvCode.text = data.code

        val minPrice = data.items.minOf { it.price }
        val maxPrice = data.items.maxOf { it.price }
        binding.tvPrice.text = if (minPrice == maxPrice) {
            minPrice.toCurrency()
        } else {
            "${minPrice.toCurrency()} ~ ${maxPrice.toCurrency()}"
        }
    }

    /**
     * Mở hộp thoại chọn loại sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/10/2022
     *
     * @version 1
     * @updated 3/10/2022: Tạo function
     */
    private fun showProductTypeSelectDialog(product: Product) {
        dialog = BottomSheetDialog(context, R.style.Theme_FreshersApplication_BottomDialog)
        dialog?.setContentView(dialogBinding.root)
        dialog?.setOnDismissListener {
            guard(color, size, unit) { cl, sz, un ->
                val item = product.items.find { it.color == cl && it.size == sz && it.unit == un }
                item?.let { onProductItemSelectedListener(it, quantity) }
            }
            // view đã được gắn vào với dialog, do vậy khi dialog bị xóa bỏ thì cũng cần gỡ bỏ liên kết
            // giữa dialog và view. Làm vậy để có thể tái sử dụng lại view mà không cần khởi tạo mới
            (dialogBinding.root.parent as ViewGroup).removeView(dialogBinding.root)
        }
        initDialogUI(product)

        dialog?.show()
    }

    /**
     * Cài đặt giao diện cho hộp thoại chọn loại sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 1
     * @updated 3/11/2022: Tạo function
     */
    private fun initDialogUI(product: Product) {
        quantity = 1
        color = null
        size = null
        unit = null

        dialogBinding.tvName.text = product.name
        dialogBinding.tvCode.text = product.code
        dialogBinding.tvQuantity.text = quantity.toString()

        dialogBinding.btnPlus.setOnClickListener {
            quantity++
            dialogBinding.tvQuantity.text = quantity.toString()
        }
        dialogBinding.btnMinus.setOnClickListener {
            if (quantity == 1) {
                Toast.makeText(context, R.string.quantity_must_be_bigger_than_0, Toast.LENGTH_SHORT)
                    .show()
            } else {
                quantity--
                dialogBinding.tvQuantity.text = quantity.toString()
            }
        }

        configRcv(product)
    }

    /**
     * Cài đặt 3 recyclerview trong 1 hàm
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 1
     * @updated 3/11/2022: Tạo function
     */
    private fun configRcv(product: Product) {
        dialogBinding.rcvColor.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        dialogBinding.rcvColor.adapter = TypeSelectorAdapter(product.items
            .map { it.color }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            color = data
            guard(color, size, unit) { _, _, _ -> dialog?.dismiss() }
        }

        dialogBinding.rcvSize.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        dialogBinding.rcvSize.adapter = TypeSelectorAdapter(product.items
            .map { it.size }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            size = data
            guard(color, size, unit) { _, _, _ -> dialog?.dismiss() }
        }

        dialogBinding.rcvUnit.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        dialogBinding.rcvUnit.adapter = TypeSelectorAdapter(product.items
            .map { it.unit }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            unit = data
            guard(color, size, unit) { _, _, _ -> dialog?.dismiss() }
        }
    }
}