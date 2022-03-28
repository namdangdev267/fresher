package com.misa.fresher.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.misa.fresher.R
import com.misa.fresher.databinding.ItemSaleProductBinding
import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.data.model.product.ProductItem
import com.misa.fresher.data.model.product.ProductUnit
import com.misa.fresher.utils.getDimension
import com.misa.fresher.utils.getDrawable

class ViewProduct(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    val binding = ItemSaleProductBinding.inflate(LayoutInflater.from(context), this)

    var productModel: ProductModel = ProductModel()
        set(value) {
            field = value
            if(value.items.size == 1) {
                color = value.items[0].color
                size = value.items[0].size
            } else {
                color = null
                size = null
            }
            isUnitSelect = false
            updateUI()
        }

    val name: String
        get() = if(items.size == 1) items[0].name else productModel.name
    val code: String
        get() = if(items.size == 1) items[0].code else productModel.code

    var amount: Int
        set(value) {
            productModel.amount = value
            updateUI()
        }
        get() = productModel.amount

    var productUnit: ProductUnit
        set(value) {
            productModel.unit = value
            updateUI()
        }
        get() = productModel.unit

    val items: List<ProductItem>
        get() = productModel.items.filter { (size == null || it.size == size) && (color == null || it.color == color) }

    private val price: String
        get() = when (items.size) {
            0 -> "0.0"
            1 -> (items[0].price * productUnit.value).toString()
            else -> "${items.minOf { it.price } * productUnit.value} ~ ${items.maxOf { it.price } * productUnit.value}"
        }

    private val totalPrice: String
        get() = price.split(" ~ ").joinToString(" ~ ") {
            (amount * (it.toDoubleOrNull() ?: 1.0)).toString()
        }


    var color: String? = null
        set(value) {
            field = value
            updateUI()
        }
    var size: String? = null
        set(value) {
            field = value
            updateUI()
        }

    private var isUnitSelect = false
        set(value) {
            field = value
            updateUI()
        }

    val colors get() = items.map { it.color }.distinct()
    val sizes get() = items.map { it.size }.distinct()
    val unitNames get() = productModel.units.map { it.name }.distinct()

    private fun updateUI() {
        binding.run {
            if (productModel.image == 0) binding.imgImage.isGone = true
            else binding.imgImage.setImageResource(productModel.image)
            binding.txtName.text = name
            binding.txtCode.text = code

            val isAmountChangeable = amount > 0
            if (isAmountChangeable) {
                txtAmount.text = amount.toString()
                txtTotalPrice.text = totalPrice
            }
            listOf(btnAdd, btnMinus, txtAmount, txtTotalPrice).forEach { it.isVisible = isAmountChangeable }
            btnUnitSelect.isVisible = isUnitSelect
            txtPrice.text = price
            txtUnit.text = if(productUnit.name.isNotEmpty()) "/${productUnit.name}" else ""
        }
    }

    init {
        layoutParams =
            LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getDimension(context, R.dimen.product_item_height))
        background = getDrawable(context, R.drawable.bg_product_item)
        setPadding(getDimension(context, R.dimen.pad_medium))
        isClickable = true
        isFocusable = true
    }
}