package com.misa.fresher.models

import com.misa.fresher.models.enum.Category
import com.misa.fresher.models.enum.Color

class Product(
    val imgProduct: Int,
    val nameProduct: String,
    val codeProduct: String,
    val color: Color,
    val category: Category,
    val priceProduct: Int,
    var quantity: Int,
    var dateArrival: String
)
//
///**
//*
//* @Author Truong Trung Kien
//* @date: 3/23/2022 11:32 AM
//**/
//@SuppressLint("Range")
//class Product(cursor: Cursor) {
//    private var imgProduct: Int = 0
//    private var nameProduct: String = ""
//    private var codeProduct: String = ""
//    private var color: Color = TODO()
//    private var priceProduct: Int = 0
//    private var quantity: Int = 0
//
//    companion object {
//        const val TABLE_NAME = "Product"
//
//        const val NAMEPRODUCT = "nameProduct"
//        const val CODEPRODUCT = "codeProduct"
//        const val COLOR = "color"
//        const val PRICEPRODUCT = "priceProduct"
//        const val QUANTITY = "quantity"
//    }
//
//    init {
//        try {
//            nameProduct = cursor.getString(cursor.getColumnIndex(NAMEPRODUCT))
//            codeProduct = cursor.getString(cursor.getColumnIndex(CODEPRODUCT))
//            priceProduct = cursor.getInt(cursor.getColumnIndex(PRICEPRODUCT))
//            quantity = cursor.getInt(cursor.getColumnIndex(QUANTITY))
//        } catch (e: Exception) {
//        }
//    }
//}

