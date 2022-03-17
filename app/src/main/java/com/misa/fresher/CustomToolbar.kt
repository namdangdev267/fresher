package com.misa.fresher

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*

class CustomToolbar : LinearLayout
{
    constructor(context: Context) : this(context, null)
    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar, 0, 0)
        val imgSearch=a.getResourceId(R.styleable.CustomToolbar_imgSerch,0)
        val txtHint=a.getString(R.styleable.CustomToolbar_searchHint)
        val imgCode=a.getResourceId(R.styleable.CustomToolbar_imgBarcode,0)
        val imgFilter=a.getResourceId(R.styleable.CustomToolbar_imgFilter,0)
        val imgCart=a.getResourceId(R.styleable.CustomToolbar_imgCart,0)
        a.recycle()
        orientation= HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.item_toolbar,this,true)
        val imbCart=findViewById<ImageView>(R.id.imb_cart)
        imbCart.setImageResource(imgCart)
        val imbSearch=findViewById<ImageView>(R.id.imb_search)
        imbSearch.setImageResource(imgSearch)
        val txtSearch=findViewById<EditText>(R.id.txt_search)
        txtSearch.setHint(txtHint)
        val imbBarcode=findViewById<ImageView>(R.id.imb_barcode)
        imbBarcode.setImageResource(imgCode)
        val imbFilter=findViewById<ImageView>(R.id.imb_filter)
        imbFilter.setImageResource(imgFilter)
    }
}