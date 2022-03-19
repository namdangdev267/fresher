package com.misa.fresher.utils

sealed class Enums {
    enum class ShipInfo {
        BASIC,
        BASIC_TWO,
        BASIC_THREE,
        CHECKBOX,
        RADIO
    }
    enum class Product {
        MODEL,
        ITEM,
        ITEM_SELECTED
    }
}