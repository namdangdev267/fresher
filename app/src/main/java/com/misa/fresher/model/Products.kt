package com.misa.fresher.model
/**
* Tạo class Products lấy từ fake data
* @Auther : NTBao
* @date : 3/18/2022
**/
data class Products(
    val id: String,
    val name: String,
    val price: Double,
    val img: Int,
    val color: String,
    val size: String
)