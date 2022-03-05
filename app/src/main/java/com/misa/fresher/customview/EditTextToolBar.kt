package com.misa.fresher.customview

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import com.misa.fresher.R


class EditTextToolBar: AppCompatEditText {
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        array = context.obtainStyledAttributes(attrs, R.styleable.EditTextToolBar, 0, 0)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        array = context.obtainStyledAttributes(attrs, R.styleable.EditTextToolBar, defStyleAttr, 0)
    }

    private var array : TypedArray? = null
    var iconStart: Int = R.drawable.ic_search
    var iconEnd: Int = R.drawable.ic_qr_scan

    init {
        array?.apply {
            iconStart = getResourceId(R.styleable.EditTextToolBar_startIcon, R.drawable.ic_search)
            iconEnd = getResourceId(R.styleable.EditTextToolBar_startIcon, R.drawable.ic_qr_scan)
        }

        background = AppCompatResources.getDrawable(context, R.drawable.bg_search)
        maxLines = 1
        ellipsize = TextUtils.TruncateAt.END
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
        setCompoundDrawablesWithIntrinsicBounds(
            AppCompatResources.getDrawable(context, iconStart), null,
            AppCompatResources.getDrawable(context, iconEnd), null)
        compoundDrawablePadding = 10
    }
}