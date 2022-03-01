package com.misa.fresher.Models

import com.misa.fresher.R

sealed class ItemShip{
    data class ItemTouch(
        var title:String,
        var hintContent:String,
        var imageResourcce:Int?,
        var asterisk:String?
    ):ItemShip()

    data class ItemCalculator(
        var title:String,
        var content:String,
        var imageResourcce:Int = R.drawable.ic_calculator
    ):ItemShip()


    data class ItemMultiContent(
        var title1:String,
        var title2:String,
        var content1:String,
        var content2:String,
        var imageResource:Int
    ):ItemShip()


    data class ItemCheck(
        var title:String
    ):ItemShip()


}
