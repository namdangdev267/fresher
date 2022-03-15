package com.misa.fresher.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.misa.fresher.R
import com.misa.fresher.databinding.ItemSaleProductBinding
import com.misa.fresher.models.product.ProductUnit

class ItemSaleProductView(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    val binding = ItemSaleProductBinding.inflate(LayoutInflater.from(context), this, true)

    var image: Int = 0
        set(value) {
            field = value
            binding.imgImage.apply {
                if (value == 0) visibility = View.GONE
                else {
                    visibility = View.VISIBLE
                    setImageResource(R.drawable.ic_product)
                }
            }
        }

    var name: String = ""
        set(value) {
            field = value
            binding.txtName.text = value
        }

    var code: String = ""
        set(value) {
            field = value
            binding.txtCode.text = value
        }

    var price: String = ""
        set(value) {
            field = value
            binding.txtPrice.apply {
                if(price == "") {
                    visibility = View.GONE
                }
                else {
                    visibility = View.VISIBLE
                    binding.txtPrice.text = getTxtPrice()
                    binding.txtTotalPrice.text = getTxtTotalPrice()
                }
            }
        }

    var amount: Int = 0
        set(value) {
            field = value
            binding.apply {
                if (value == 0) {
                    btnAdd.visibility = View.GONE
                    btnMinus.visibility = View.GONE
                    txtAmount.visibility = View.GONE
                    txtTotalPrice.visibility = View.GONE
                } else {
                    btnAdd.visibility = View.VISIBLE
                    btnMinus.visibility = View.VISIBLE
                    txtAmount.visibility = View.VISIBLE
                    txtTotalPrice.visibility = View.VISIBLE

                    txtAmount.text = value.toString()
                    txtTotalPrice.text = getTxtTotalPrice()
                }
            }
        }

    var unit: ProductUnit = ProductUnit("", 1)
        set(unit) {
            field = unit
            binding.txtUnit.text = if (unit.name == "") "" else "/${unit.name}"

            binding.txtPrice.text = getTxtPrice()
            binding.txtTotalPrice.text = getTxtTotalPrice()

        }

    var isUnitSelect: Boolean = false
        set(value) {
            field = value
            binding.btnUnitSelect.visibility = if (value) View.VISIBLE else View.GONE
        }

    private fun getTxtTotalPrice(): String {
        return price.split(" ~ ").joinToString(" ~ ") {
            (amount * (it.toDoubleOrNull() ?: 1.0) * unit.value).toString()
        }
    }
    private fun getTxtPrice(): String {
        return price.split(" ~ ").joinToString(" ~ ") {
            ((it.toDoubleOrNull() ?: 1.0) * unit.value).toString()
        }
    }
    init {
        context.obtainStyledAttributes(attrs, R.styleable.ItemSaleProductView).apply {
            image = getResourceId(R.styleable.BtnCalculatorKey_icon, 0)
            name = getString(R.styleable.ItemSaleProductView_name) ?: ""
            code = getString(R.styleable.ItemSaleProductView_code) ?: ""
            price = getString(R.styleable.ItemSaleProductView_price) ?: ""
            amount = getInt(R.styleable.ItemSaleProductView_amount, 0)
            isUnitSelect = getBoolean(R.styleable.ItemSaleProductView_isUnitSelect, false)
        }.recycle()
    }
}