package com.misa.fresher.data.models

import com.misa.fresher.R

sealed class ItemShipInfor {
    data class ItemTouch(
        var title:String,
        var hintContent:String,
        var imageResourcce:Int?,
        var require:String?
    ): ItemShipInfor()

    data class ItemCalculator(
        var title:String,
        var content:String,
        var imageResourcce:Int = R.drawable.ic_baseline_calculate_24
    ): ItemShipInfor()

    data class ItemMultiContent(
        var title1:String,
        var title2:String,
        var content1:String,
        var content2:String,
        var imageResource:Int
    ): ItemShipInfor()

    data class ItemCheck(
        var title:String
    ): ItemShipInfor()

    data class ItemRadioButton(
        var option1:String,
        var option2:String
    ): ItemShipInfor()

    data class Item3Col(
        var title:String,
        var content1:String,
        var content2:String,
        var content3:String
    ): ItemShipInfor()
}