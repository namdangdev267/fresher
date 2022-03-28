package com.misa.fresher.ui.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.misa.fresher.R
import com.misa.fresher.databinding.CustomRecyclerViewBinding

class CustomRecyclerView : LinearLayout {
    constructor(context: Context) : super(context)

    val bindingCustomRecyclerView: CustomRecyclerViewBinding by lazy {
        CustomRecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val array = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomRecyclerView, 0, 0
        )
        val title = array.getString(R.styleable.CustomRecyclerView_title1)
        Log.e(this.javaClass.simpleName, title.toString())
        array.recycle()
        LayoutInflater.from(context).inflate(R.layout.custom_recycler_view, this, true)
        val titleRcv: TextView = bindingCustomRecyclerView.cvRcvTitle
        titleRcv.text = title.toString()
    }
}