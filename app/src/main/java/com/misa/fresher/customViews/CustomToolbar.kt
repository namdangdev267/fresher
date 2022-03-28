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
import com.misa.fresher.databinding.ActivityLoginBinding
import com.misa.fresher.databinding.CustomToolbarBinding

/**
 * Tạo custom view cho toolbar
 * @Auther : NTBao
 * @date : 3/18/2022
 **/
class CustomToolbar : LinearLayout {
    val binding = CustomToolbarBinding.inflate(LayoutInflater.from(context), this)
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

//        val view = LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true)

//        setBackgroundColor(R.color.purple_700)
        orientation = HORIZONTAL

        val ivSearch = binding.ivSearch
        ivSearch.setImageResource(ivSearchSrc)
        ivSearch.background = AppCompatResources.getDrawable(context, ivSearchSrcBg)
        val edSearch = binding.edSearch
        edSearch.setHint(edSearchHint)
        edSearch.background = AppCompatResources.getDrawable(context, edSearchBg)
        val btnQrCode = binding.btnQrCode
        btnQrCode.setImageResource(btnQrCodeSrc)
        btnQrCode.background = AppCompatResources.getDrawable(context, btnQrCodeBg)
        val btnCart = binding.btnCart
        btnCart.setImageResource(btnCartSrc)
        val btnFilter = binding.btnFilter
        btnFilter.setImageResource(btnFilterSrc)

    }
}
