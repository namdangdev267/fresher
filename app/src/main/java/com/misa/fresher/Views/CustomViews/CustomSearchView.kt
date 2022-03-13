package com.misa.fresher.Views.CustomViews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.misa.fresher.R

class CustomSearchView: LinearLayout {
    constructor(context: Context) : super(context)

    lateinit var editText:EditText

    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attrs:AttributeSet): super(context,attrs)
    {
        val array = context.obtainStyledAttributes(attrs,
            R.styleable.CustomSearchView,0,0)
        val title = array.getString(R.styleable.CustomSearchView_title)
        var icon1 = array.getResourceId(R.styleable.CustomSearchView_icon1,0)
        var icon2 = array.getResourceId(R.styleable.CustomSearchView_icon2,0)
        var icon3 = array.getResourceId(R.styleable.CustomSearchView_icon3,0)
//        val resourceId = array.getResourceId(R.styleable.SearchView_image,0)
        array.recycle()

        setBackgroundColor(R.color.background)
        orientation= HORIZONTAL

        LayoutInflater.from(context).inflate(R.layout.custom_search_view,this,true)

        editText = findViewById<EditText>(R.id.edittext_search_hint)
        editText.hint =  title

        var search_icon1:ImageView = findViewById(R.id.imageview_search_icon1)
        search_icon1.setImageResource(icon1)

        var search_icon2 = findViewById<ImageView>(R.id.imageview_search_icon2)
        search_icon2.setImageResource(icon2)

        var search_icon3 = findViewById<ImageView>(R.id.imageview_search_icon3)
        search_icon3.setImageResource(icon3)

    }


}