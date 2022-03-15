package com.misa.fresher.customview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.misa.fresher.R

class BtnRound(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    private val scale = resources.displayMetrics.density
    private fun DPtoPX(dp: Int): Int = (dp * scale + 0.5f).toInt()

    private val DEFAULT_DIAMETER = DPtoPX(50)
    private val DEFAULT_MARGIN = DPtoPX(6)
    private val DEFAULT_PADDING = DEFAULT_DIAMETER/2 - DPtoPX(12)

    private val DEFAULT_BG_ACTIVE by lazy { AppCompatResources.getDrawable(context, R.drawable.bg_round_25dp_purple_active) }
    private val DEFAULT_BG by lazy { AppCompatResources.getDrawable(context, R.drawable.bg_round_25dp_purple_light) }

    private val DEFAULT_TXT_ACTIVE by lazy { ContextCompat.getColor(context, R.color.white) }
    private val DEFAULT_TXT by lazy { ContextCompat.getColor(context, R.color.greyDark) }

    var value: String = ""
        set(value) {
            field = value
            text = value
        }
    var icon: Int = 0
        set(value) {
            field = value
            setCompoundDrawablesWithIntrinsicBounds(field, 0, 0, 0)
            if(field != 0) { setPadding( DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING,  DEFAULT_PADDING) }
        }
    var isActive: Boolean = false
        set(value) {
            field = value
            if (value) {
                background = DEFAULT_BG_ACTIVE
                setTextColor(DEFAULT_TXT_ACTIVE)
            } else {
                background = DEFAULT_BG
                setTextColor(DEFAULT_TXT)
            }
            if(icon != 0) { setPadding( DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING,  DEFAULT_PADDING) }
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.BtnRound).apply {
            value = getString(R.styleable.BtnRound_value) ?: ""
            isActive = getBoolean(R.styleable.BtnRound_isActive, false)
            icon = getResourceId(R.styleable.BtnRound_icon, 0)
        }.recycle()

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(DEFAULT_MARGIN)
        layoutParams = params
        minWidth = DEFAULT_DIAMETER
        minHeight = DEFAULT_DIAMETER
        gravity = Gravity.CENTER
    }
}