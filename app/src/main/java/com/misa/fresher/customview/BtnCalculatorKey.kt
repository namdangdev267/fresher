package com.misa.fresher.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.misa.fresher.R


class BtnCalculatorKey(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    var value: String = ""
        set(value) {
            field = value
            text = value
            if(field == "") {
                measure(0, 0)
                setPadding( measuredWidth - 12, 0, measuredWidth - 12,  0)
            }
        }
    var icon: Int = 0
        set(value) {
            field = value
            setCompoundDrawablesWithIntrinsicBounds(field, 0, 0, 0)
        }
    var isActive: Boolean = false
        set(value) {
            field = value
            if(value) {
                background = AppCompatResources.getDrawable(context, R.drawable.bg_calculator_key_active)
                setTextColor(ContextCompat.getColor(context, R.color.white))
            } else {
                background = AppCompatResources.getDrawable(context, R.drawable.bg_calculator_key)
                setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.BtnCalculatorKey).apply {
            value = getString(R.styleable.BtnCalculatorKey_value) ?: ""
            isActive = getBoolean(R.styleable.BtnCalculatorKey_isActive, false)
            icon = getResourceId(R.styleable.BtnCalculatorKey_icon, 0)
        }.recycle()
    }
}
