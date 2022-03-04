package com.misa.fresher

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout

class EditTextCustom : RelativeLayout {
    constructor(context: Context) : super(context)

    @SuppressLint("ResourceColor")
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.EditText, 0, 0)
        array.recycle()

        setBackgroundResource(R.drawable.custom_searchview)

        LayoutInflater.from(context).inflate(R.layout.edit_text_main, this, true)

        val hintEdt = findViewById<EditText>(R.id.edtSearch)
        hintEdt.hint = "Enter name, SKU code, S......."
    }
}