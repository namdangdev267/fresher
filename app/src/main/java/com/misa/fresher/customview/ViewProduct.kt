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
import com.misa.fresher.data.model.product.Product
import com.misa.fresher.data.model.product.ProductItem
import com.misa.fresher.data.model.product.ProductUnit
import com.misa.fresher.utils.getDimension
import com.misa.fresher.utils.getDrawable

class ViewProduct(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    val binding = ItemSaleProductBinding.inflate(LayoutInflater.from(context), this, true)

    var product: Product = Product()
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
        get() {
            return if(color != null && size != null) product.name + "(${color}/${size})"
            else product.name
        }
    val code: String
        get() {
            return if(color != null && size != null) product.code + "-${color!!.slice(0..2)}-${size!![0]}"
            else product.code
        }

    var amount: Int
        set(value) {
            product.amount = value
            updateUI()
        }
        get() = product.amount

    var unit: ProductUnit
        set(value) {
            product.unit = value
            updateUI()
        }
        get() = product.unit

    val items: List<ProductItem>
        get() = product.items.filter { (size == null || it.size == size) && (color == null || it.color == color) }

    private val price: String
        get() = when (items.size) {
            0 -> "0.0"
            1 -> (items[0].price * unit.value).toString()
            else -> "${items.minOf { it.price } * unit.value} ~ ${items.maxOf { it.price } * unit.value}"
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

    var isUnitSelect = false
        set(value) {
            field = value
            updateUI()
        }

    val colors get() = items.map { it.color }.distinct()
    val sizes get() = items.map { it.size }.distinct()
    val unitNames get() = product.units.map { it.name }.distinct()

    fun updateUI() {
        binding.run {
            if (product.image == 0) binding.imgImage.isGone = true
            else binding.imgImage.setImageResource(product.image)
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
            txtUnit.text = if(unit.name.isNotEmpty()) "/${unit.name}" else ""
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