package com.misa.fresher.views.customViews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.misa.fresher.R
import com.misa.fresher.databinding.CustomSearchViewBinding

class CustomSearchView : LinearLayout {
    constructor(context: Context) : super(context)

    lateinit var editText: EditText
    lateinit var binding:CustomSearchViewBinding

    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val array = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomSearchView, 0, 0
        )
        val title = array.getString(R.styleable.CustomSearchView_title)
        val icon1 = array.getResourceId(R.styleable.CustomSearchView_icon1, 0)
        val icon2 = array.getResourceId(R.styleable.CustomSearchView_icon2, 0)
        val icon3 = array.getResourceId(R.styleable.CustomSearchView_icon3, 0)
//        val resourceId = array.getResourceId(R.styleable.SearchView_image,0)
        array.recycle()

        setBackgroundColor(R.color.background)
        orientation = HORIZONTAL

        LayoutInflater.from(context).inflate(R.layout.custom_search_view, this, true)

        binding = CustomSearchViewBinding.inflate(LayoutInflater.from(context),this)

        editText = binding.edittextSearchHint
        editText.hint = title

        var search_icon1 = binding.imageviewSearchIcon1
        search_icon1.setImageResource(icon1)

        var search_icon2 = binding.imageviewSearchIcon2
        search_icon2.setImageResource(icon2)

        var search_icon3 = binding.imageviewSearchIcon3
        search_icon3.setImageResource(icon3)

    }


}