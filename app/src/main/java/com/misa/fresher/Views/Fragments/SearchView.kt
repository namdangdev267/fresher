package com.misa.fresher.Views.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.misa.fresher.R

class SearchView: LinearLayout {
    constructor(context: Context) : super(context)

    lateinit var hint:EditText

    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attrs:AttributeSet): super(context,attrs)
    {
        val array = context.obtainStyledAttributes(attrs,
            R.styleable.SearchView,0,0)
        val title = array.getString(R.styleable.SearchView_title)
        var icon1 = array.getResourceId(R.styleable.SearchView_icon1,0)
        var icon2 = array.getResourceId(R.styleable.SearchView_icon2,0)
        var icon3 = array.getResourceId(R.styleable.SearchView_icon3,0)
//        val resourceId = array.getResourceId(R.styleable.SearchView_image,0)
        array.recycle()

        setBackgroundColor(R.color.background)
        orientation= HORIZONTAL

        LayoutInflater.from(context).inflate(R.layout.search_view,this,true)

        hint = findViewById<EditText>(R.id.edittext_search_hint)
        hint.hint =  title

        var search_icon1:ImageView = findViewById(R.id.imageview_search_icon1)
        search_icon1.setImageResource(icon1)

        var search_icon2 = findViewById<ImageView>(R.id.imageview_search_icon2)
        search_icon2.setImageResource(icon2)

        var search_icon3 = findViewById<ImageView>(R.id.imageview_search_icon3)
        search_icon3.setImageResource(icon3)

    }

    fun setEditTextClick(listener: (string:String)->Unit)
    {
        hint.doAfterTextChanged {
            listener(hint.text.toString())
        }
    }

}