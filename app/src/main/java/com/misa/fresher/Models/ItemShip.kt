package com.misa.fresher.Models

import com.misa.fresher.R

sealed class ItemShip{
    data class ItemTouch(
        var title:String,
        var hintContent:String,
        var imageResourcce:Int?,
        var require:String?
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

    data class ItemRadioButton(
        var option1:String,
        var option2:String
    ):ItemShip()

    data class Item3Col(
        var title:String,
        var content1:String,
        var content2:String,
        var content3:String
    ):ItemShip()



}
