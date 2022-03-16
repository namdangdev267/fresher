package com.misa.fresher.Views.CustomViews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misa.fresher.R

class CustomRecyclerView : LinearLayout {
    constructor(context: Context) : super(context)

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


        var titleRcv: TextView = findViewById(R.id.cv_rcv_title)
        titleRcv.text = title.toString()


    }


}