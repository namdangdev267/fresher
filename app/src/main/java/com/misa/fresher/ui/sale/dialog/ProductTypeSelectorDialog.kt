package com.misa.fresher.ui.sale.dialog

import android.content.Context
import androidx.annotation.StyleRes
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.misa.fresher.R
import com.misa.fresher.data.entity.Product
import com.misa.fresher.data.entity.ProductColor
import com.misa.fresher.data.entity.ProductSize
import com.misa.fresher.data.entity.ProductUnit
import com.misa.fresher.databinding.DialogProductTypeSelectorBinding
import com.misa.fresher.ui.sale.adapter.TypeSelectorAdapter
import com.misa.fresher.util.guard
import com.misa.fresher.util.toast

/**
 * Dialog chọn loại mặt hàng, số lượng hàng để cho vào giỏ
 *
 * @author Nguyễn Công Chính
 * @since 3/31/2022
 *
 * @version 1
 * @updated 3/31/2022: Tạo class
 */
class ProductTypeSelectorDialog(
    private val context: Context,
    @StyleRes resId: Int,
    private val binding: DialogProductTypeSelectorBinding,
    private val selectSuccessListener: (Product, Int, ProductColor, ProductSize, ProductUnit) -> Unit
) {

    private val dialog by lazy { BottomSheetDialog(context, resId) }

    private var quantity = 1
    private var color: ProductColor? = null
    private var size: ProductSize? = null
    private var unit: ProductUnit? = null

    init {
        configDialog()
    }

    /**
     * Hàm cài đặt ban đầu cho dialog
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    private fun configDialog() {
        dialog.setContentView(binding.root)
    }

    /**
     * Hàm cài đặt các sự kiện, thay đổi dữ liệu hiển thị lên dialog
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    private fun bindData(product: Product) {
        dialog.setOnDismissListener {
            guard(color, size, unit) { cl, sz, un ->
                selectSuccessListener(product, quantity, cl, sz, un)
            }
        }
        initDialogUI(product)
    }

    /**
     * Cài đặt giao diện cho hộp thoại chọn loại sản phẩm
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 3
     * @updated 3/11/2022: Tạo function
     * @updated 3/15/2022: Chuyển nhà từ [com.misa.fresher.ui.sale.adapter.viewholder.ProductViewHolder] sang đây
     * @updated 3/31/2022: Chuyển nhà từ [com.misa.fresher.ui.sale.SaleFragment] sang đây
     */
    private fun initDialogUI(product: Product) {
        quantity = 1
        color = null
        size = null
        unit = null

        binding.tvName.text = product.name
        binding.tvCode.text = product.code
        binding.tvQuantity.text = quantity.toString()

        binding.btnPlus.setOnClickListener {
            quantity++
            binding.tvQuantity.text = quantity.toString()
        }
        binding.btnMinus.setOnClickListener {
            if (quantity == 1) {
                toast(context, R.string.quantity_must_be_bigger_than_0)
            } else {
                quantity--
                binding.tvQuantity.text = quantity.toString()
            }
        }

        config3RcvInDialog(product)
    }

    /**
     * Cài đặt 3 recyclerview trong hộp thoại chọn sản phẩm trong 1 hàm
     *
     * @author Nguyễn Công Chính
     * @since 3/11/2022
     *
     * @version 3
     * @updated 3/11/2022: Tạo function
     * @updated 3/15/2022: Chuyển nhà từ [com.misa.fresher.ui.sale.adapter.viewholder.ProductViewHolder] sang đây
     * @updated 3/31/2022: Chuyển nhà từ [com.misa.fresher.ui.sale.SaleFragment] sang đây
     */
    private fun config3RcvInDialog(product: Product) {
        binding.rcvColor.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        binding.rcvColor.adapter = TypeSelectorAdapter(product.items
            .map { it.color }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            color = data
            guard(color, size, unit) { _, _, _ -> dismiss() }
        }

        binding.rcvSize.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        binding.rcvSize.adapter = TypeSelectorAdapter(product.items
            .map { it.size }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            size = data
            guard(color, size, unit) { _, _, _ -> dismiss() }
        }

        binding.rcvUnit.layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
        binding.rcvUnit.adapter = TypeSelectorAdapter(product.items
            .map { it.unit }
            .let { item -> item.distinctBy { it.id } }
            .toMutableList()
        ) { data ->
            unit = data
            guard(color, size, unit) { _, _, _ -> dismiss() }
        }
    }

    /**
     * Hàm hiển thị dialog
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    fun show(product: Product) {
        bindData(product)
        dialog.show()
    }

    /**
     * Hàm ẩn đi dialog
     *
     * @author Nguyễn Công Chính
     * @since 3/31/2022
     *
     * @version 1
     * @updated 3/31/2022: Tạo function
     */
    fun dismiss() {
        dialog.dismiss()
    }
}