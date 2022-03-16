package com.misa.fresher.model


data class Bill(
    var listSelectedProduct: MutableList<SelectedProducts>?,
    val id: Int,
    var receiver: Receiver?
)