package com.misa.fresher.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.misa.fresher.R

class CustomSearchView : LinearLayout {
    constructor(context : Context) : super(context)

    constructor(context: Context,attrs : AttributeSet) : super (context,attrs)
    {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomSearchView,0,0)
        val icon1 =a.getResourceId(R.styleable.CustomSearchView_imagebutton1,0)
        val title = a.getString(R.styleable.CustomSearchView_title)
        val icon2 =a.getResourceId(R.styleable.CustomSearchView_imagebutton2,0)
        val icon3 =a.getResourceId(R.styleable.CustomSearchView_imagebutton3,0)
        val icon4 =a.getResourceId(R.styleable.CustomSearchView_imagebutton4,0)

        a.recycle()

        LayoutInflater.from(context).inflate(R.layout.custom_searchview, this, true)

        val icon1_imagebutton = findViewById<ImageView>(R.id.img_btn_search)
        icon1_imagebutton.setImageResource(icon1)
        val title_et = findViewById<TextView>(R.id.et_search)
        title_et.hint = title
        val icon2_imagebutton = findViewById<ImageView>(R.id.img_btn_codeqr)
        icon2_imagebutton.setImageResource(icon2)
        val icon3_imagebutton = findViewById<ImageView>(R.id.img_btn_icon3)
        icon3_imagebutton.setImageResource(icon3)
        val icon4_imagebutton = findViewById<ImageView>(R.id.img_btn_icon4)
        icon4_imagebutton.setImageResource(icon4)
    }
}