package com.misa.fresher.util

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
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

/**
 * Tương tự ?.let nhưng hoạt động với 2 biến
 *
 * @author Nguyễn Công Chính
 * @since 3/11/2022
 *
 * @version 2
 * @updated 3/11/2022: Tạo function
 * @updated 3/12/2022: Thêm generic cho hàm
 */
fun <T1, T2>guard(variable1: T1?, variable2: T2?, function: (T1, T2) -> Unit) {
    if (variable1 != null && variable2 != null) {
        function(variable1, variable2)
    }
}

/**
 * Tương tự ?.let nhưng hoạt động với 3 biến
 *
 * @author Nguyễn Công Chính
 * @since 3/11/2022
 *
 * @version 2
 * @updated 3/11/2022: Tạo function
 * @updated 3/12/2022: Thêm generic cho hàm
 */
fun <T1, T2, T3> guard(variable1: T1?, variable2: T2?, variable3: T3?, function: (T1, T2, T3) -> Unit) {
    if (variable1 != null && variable2 != null && variable3 != null) {
        function(variable1, variable2, variable3)
    }
}

/**
 * Hàm check null, nếu null sẽ trả về giá trị mặc định
 *
 * @author Nguyễn Công Chính
 * @since 3/15/2022
 *
 * @version 1
 * @updated 3/15/2022: Tạo function
 */
fun <T> default(variable: T?, defaultValue: T): T {
    if (variable != null) {
        return variable
    }
    return defaultValue
}

/**
 * Hàm lấy dữ liệu trong bundle, nếu không thành công, trả về default value
 *
 * @author Nguyễn Công Chính
 * @since 3/14/2022
 *
 * @version 1
 * @updated 3/14/2022: Tạo function
 */
inline fun <reified T: Any> Bundle?.get(name: String, defaultValue: T): T {
    this?.let {
        val result = this.get(name)
        if (result != null && result is T) {
            return result
        }
    }
    return defaultValue
}

/**
 * Hiển thị toast với đầu vào là string
 *
 * @author Nguyễn Công Chính
 * @since 3/16/2022
 *
 * @version 1
 * @updated 3/16/2022: Tạo function
 */
fun toast(context: Context, text: String, isLong: Boolean = false) {
    Toast.makeText(
        context,
        text,
        if (isLong) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    ).show()
}

/**
 * Hiển thị toast với đầu vào là string id
 *
 * @author Nguyễn Công Chính
 * @since 3/16/2022
 *
 * @version 1
 * @updated 3/16/2022: Tạo function
 */
fun toast(context: Context, textId: Int, isLong: Boolean = false) {
    Toast.makeText(
        context,
        context.getString(textId),
        if (isLong) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    ).show()
}