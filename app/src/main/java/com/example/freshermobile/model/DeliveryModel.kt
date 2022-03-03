package com.example.freshermobile.model

sealed class DeliveryModel {
    data class EnterEditText(
        val title: String,
        val star: String? = null,
        val content: String,
        val moreAction: Int? = null
    ) : DeliveryModel()

    data class Enter2Column (
        val title1: String,
        val content1: String,
        val moreAction: Int? = null,
        val title2: String,
        val content2: String
    ) : DeliveryModel()

    data class EnterTextView(
        val title: String,
        val star: String? = null,
        val content: String,
        val moreAction: Int? = null
    ) : DeliveryModel()

    data class Checkbox(
        val title: String,
    ) : DeliveryModel()

    data class PackageSize(
        val title: String,
        val width: Int,
        val depth: Int,
        val height: Int,
    ) : DeliveryModel()

    data class RadioModel(
        val title: String?= null,
        val radText1: String,
        val radText2: String,
    ) : DeliveryModel()
}