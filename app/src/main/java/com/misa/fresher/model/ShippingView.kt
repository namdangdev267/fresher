package com.misa.fresher.model

sealed class ShippingView {
    data class type1(
        val tittle: String,
        val asterisk: String?,
        val hint: String,
        val img: Int?
    ) : ShippingView()

    data class type2(
        val tittle: String,
        val hint: String,
        val img: Int
    ) : ShippingView()

    data class type3(
        val context1: String,
        val context2: String,
        val context3: String,
        val context4: String,
        val img: Int
    ) : ShippingView()

    data class type4(val context: String) : ShippingView()
}