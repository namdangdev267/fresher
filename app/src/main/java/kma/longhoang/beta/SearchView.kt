package kma.longhoang.beta

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout

class SearchView : LinearLayout{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        val style: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchView, 0, 0)
        val editTextHint = style.getString(R.styleable.SearchView_hint)
        val imgSearchId = style.getResourceId(R.styleable.SearchView_imageSearch, 0)
        val imgSKUid = style.getResourceId(R.styleable.SearchView_imageSKU, 0)
        style.recycle()
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
        isClickable = true
        setBackgroundResource(R.drawable.custom_toolbar)
        LayoutInflater.from(context).inflate(R.layout.searchview_layout, this, true)
        val imgViewSearch = findViewById<ImageView>(R.id.img_search)
        val imgViewSKU = findViewById<ImageView>(R.id.img_SKU)
        val edtTextSearch = findViewById<EditText>(R.id.edt_search_sp)
        imgViewSearch.setImageResource(imgSearchId)
        imgViewSKU.setImageResource(imgSKUid)
        edtTextSearch.hint = editTextHint
    }
}