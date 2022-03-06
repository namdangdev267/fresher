package com.misa.fresher.util.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.misa.fresher.R
import com.misa.fresher.databinding.EtSearchboxBinding

class CustomSearchBox : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val typedArr = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText, 0, 0)
        val hint = typedArr.getString(R.styleable.CustomEditText_hint)
        val icon = typedArr.getResourceId(R.styleable.CustomEditText_icon, 0)
        val buttonIcon = typedArr.getResourceId(R.styleable.CustomEditText_buttonIcon, 0)
        typedArr.recycle()

        val binding = EtSearchboxBinding.inflate(LayoutInflater.from(context), this, true)

        binding.etInput.hint = hint
        binding.etInput.setCompoundDrawablesRelativeWithIntrinsicBounds(icon, 0, 0, 0)
        binding.btnInput.setImageResource(buttonIcon)

    }
}