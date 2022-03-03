package com.misa.fresher.model

sealed class ShippingView {
    data class TouchTextView(
        val tittle: String,
        val asterisk: String?,
        val hint: String,
        val img: Int?
    ) : ShippingView()

    data class TouchEditText(
        val tittle: String,
        val hint: String,
        val img: Int?
    ) : ShippingView()

    data class TwoCol(
        val tittlecol1: String,
        val hintCol1: String,
        val tittleCol2: String,
        val hintCol2: String,
        val img: Int
    ) : ShippingView()

    data class CheckBox(val context: String) : ShippingView()
    data class RadionGroup(val radionBtn1: String, val radionBtn2: String) : ShippingView()
    data class ThreeCol(
        val tittleCol1: String, val hintCol1: String,
        val hintCol2: String, val hintCol3: String
    ) : ShippingView()
}