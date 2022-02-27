package com.misa.fresher.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun Float.toCurrency(): String {
    val symbols = DecimalFormatSymbols()
    symbols.decimalSeparator = ','
    symbols.groupingSeparator = '.'
    val formatter = DecimalFormat("#,##0.00", symbols)
    return formatter.format(this)
}