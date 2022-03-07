package com.misa.fresher.customview

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.misa.fresher.R


class EditTextToolBar(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private val scale: Float = context.resources.displayMetrics.density
    private fun dpToPx(dp: Int): Int = (dp * scale + 0.5f).toInt()

    var iconStart: Int = R.drawable.ic_search
        set(value) {
            field = value
            setCompoundDrawablesWithIntrinsicBounds(
                AppCompatResources.getDrawable(context, iconStart), null,
                AppCompatResources.getDrawable(context, iconEnd), null)
        }
    var iconEnd: Int = R.drawable.ic_qr_scan
        set(value) {
            field = value
            setCompoundDrawablesWithIntrinsicBounds(
                AppCompatResources.getDrawable(context, iconStart), null,
                AppCompatResources.getDrawable(context, iconEnd), null)
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.EditTextToolBar, 0, 0).apply {
            iconStart = getResourceId(R.styleable.EditTextToolBar_startIcon, R.drawable.ic_search)
            iconEnd = getResourceId(R.styleable.EditTextToolBar_startIcon, R.drawable.ic_qr_scan)
        }.recycle()

        background = AppCompatResources.getDrawable(context, R.drawable.bg_search)
        maxLines = 1
        ellipsize = TextUtils.TruncateAt.END
        gravity = Gravity.CENTER
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f)
        compoundDrawablePadding = dpToPx(4)
    }
}