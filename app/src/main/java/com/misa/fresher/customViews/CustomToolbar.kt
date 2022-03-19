package com.misa.fresher.customViews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import com.misa.fresher.R

/**
 * Tạo custom view cho toolbar
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class CustomToolbar : LinearLayout {
    constructor(context: Context) : super(context, null)

    @SuppressLint("ResourceAsColor")
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar, 0, 0)
        val ivSearchSrc = a.getResourceId(R.styleable.CustomToolbar_ivSearchSrc, 0)
        val ivSearchSrcBg = a.getResourceId(R.styleable.CustomToolbar_ivSearchSrcBg, 0)
        val edSearchBg = a.getResourceId(R.styleable.CustomToolbar_edSearchBg, 0)
        val edSearchHint = a.getString(R.styleable.CustomToolbar_edSearchHint)
        val btnQrCodeBg = a.getResourceId(R.styleable.CustomToolbar_btnQrCodeBg, 0)
        val btnQrCodeSrc = a.getResourceId(R.styleable.CustomToolbar_btnQrCodeSrc, 0)
        val btnCartSrc = a.getResourceId(R.styleable.CustomToolbar_btnCartSrc, 0)
        val btnFilterSrc = a.getResourceId(R.styleable.CustomToolbar_btnFilterSrc, 0)
        a.recycle()

        val view = LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true)

//        setBackgroundColor(R.color.purple_700)
        orientation = HORIZONTAL

        val ivSearch = view.findViewById<ImageView>(R.id.ivSearch)
        ivSearch.setImageResource(ivSearchSrc)
        ivSearch.background = AppCompatResources.getDrawable(context, ivSearchSrcBg)
        val edSearch = view.findViewById<EditText>(R.id.edSearch)
        edSearch.setHint(edSearchHint)
        edSearch.background = AppCompatResources.getDrawable(context, edSearchBg)
        val btnQrCode = view.findViewById<ImageButton>(R.id.btnQrCode)
        btnQrCode.setImageResource(btnQrCodeSrc)
        btnQrCode.background = AppCompatResources.getDrawable(context, btnQrCodeBg)
        val btnCart = view.findViewById<ImageButton>(R.id.btnCart)
        btnCart.setImageResource(btnCartSrc)
        val btnFilter = view.findViewById<ImageButton>(R.id.btnFilter)
        btnFilter.setImageResource(btnFilterSrc)

    }
}
