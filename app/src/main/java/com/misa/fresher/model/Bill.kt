package com.misa.fresher.model

/**
* Tạo class dùng để lưu bill
* @Auther : NTBao
* @date : 3/18/2022
**/
data class Bill(
    var listSelectedProduct: MutableList<SelectedProducts>?,
    val id: Int,
    var customer: Customer?
)