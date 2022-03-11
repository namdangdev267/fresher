package com.misa.fresher.util

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.res.ResourcesCompat
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

/**
 * Tìm drawable sử dụng ResourceCompat
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 1
 * @updated 3/10/2022: Tạo function
 */
fun Resources.getDrawableById(drawableId: Int): Drawable? {
    return ResourcesCompat.getDrawable(this, drawableId, null)
}

/**
 * Chuyển từ số thực sang string dưới dạng Tiền, vd: 100,000.00
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 1
 * @updated 3/10/2022: Tạo function
 */
fun Double.toCurrency(): String {
    val symbols = DecimalFormatSymbols()
    symbols.decimalSeparator = ','
    symbols.groupingSeparator = '.'
    val formatter = DecimalFormat("#,##0.00", symbols)
    return formatter.format(this)
}

/**
 * Tìm màu sử dụng ResourceCompat
 *
 * @author Nguyễn Công Chính
 * @since 3/10/2022
 *
 * @version 1
 * @updated 3/10/2022: Tạo function
 */
fun Resources.getColorById(colorId: Int): ColorStateList? {
    return ResourcesCompat.getColorStateList(this, colorId, null)
}