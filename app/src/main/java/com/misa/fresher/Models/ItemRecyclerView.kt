package com.misa.fresher.Models

import com.misa.fresher.R

sealed class ItemRecyclerView{
    data class ItemTouch(
        var title:String,
        var hintContent:String,
        var imageResourcce:Int?,
        var require:String?
    ):ItemRecyclerView()

    data class ItemCalculator(
        var title:String,
        var content:String,
        var imageResourcce:Int = R.drawable.ic_calculator
    ):ItemRecyclerView()

    data class ItemMultiContent(
        var title1:String,
        var title2:String,
        var content1:String,
        var content2:String,
        var imageResource:Int
    ):ItemRecyclerView()

    data class ItemCheck(
        var title:String
    ):ItemRecyclerView()

    data class ItemRadioButton(
        var option1:String,
        var option2:String
    ):ItemRecyclerView()

    data class Item3Col(
        var title:String,
        var content1:String,
        var content2:String,
        var content3:String
    ):ItemRecyclerView()



}
