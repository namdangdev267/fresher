package com.misa.fresher.common

import com.misa.fresher.data.entity.*

/**
 * Static class chứa các hằng số sử dụng trong chương trình
 *
 * @author Nguyễn Công Chính
 * @since 3/21/2022
 *
 * @version 2
 * @updated 3/21/2022: Tạo class
 * @updated 3/25/2022: Thêm các câu query tạo, xóa bảng sử dụng trong sqlite
 */
object Constant {
    const val BASE_URL = "https://identitytoolkit.googleapis.com/"

    const val DATABASE_NAME = "eshop_database"
    const val DATABASE_VERSION = 1

    const val CREATE_CATEGORY_TABLE = (
            "CREATE TABLE ${Category.TABLE_NAME} ("
                    + "${Category.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${Category.NAME} TEXT NOT NULL)"
            )
    const val CREATE_CUSTOMER_TABLE = (
            "CREATE TABLE ${Customer.TABLE_NAME} ("
                    + "${Customer.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${Customer.NAME} TEXT NOT NULL, "
                    + "${Customer.TEL} TEXT NOT NULL, "
                    + "${Customer.ADDRESS} TEXT NOT NULL)"
            )
    const val CREATE_PRODUCT_COLOR_TABLE = (
            "CREATE TABLE ${ProductColor.TABLE_NAME} ("
                    + "${ProductColor.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${ProductColor.NAME} TEXT NOT NULL, "
                    + "${ProductColor.CODE} TEXT NOT NULL)"
            )
    const val CREATE_PRODUCT_SIZE_TABLE = (
            "CREATE TABLE ${ProductSize.TABLE_NAME} ("
                    + "${ProductSize.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${ProductSize.NAME} TEXT NOT NULL, "
                    + "${ProductSize.CODE} TEXT NOT NULL)"
            )
    const val CREATE_PRODUCT_UNIT_TABLE = (
            "CREATE TABLE ${ProductUnit.TABLE_NAME} ("
                    + "${ProductUnit.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${ProductUnit.NAME} TEXT NOT NULL)"
            )
    const val CREATE_PRODUCT_ITEM_TABLE = (
            "CREATE TABLE ${ProductItem.TABLE_NAME} ("
                    + "${ProductItem.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${ProductItem.COLOR_ID} INTEGER NOT NULL, "
                    + "${ProductItem.SIZE_ID} INTEGER NOT NULL, "
                    + "${ProductItem.UNIT_ID} INTEGER NOT NULL, "
                    + "${ProductItem.PRICE} REAL NOT NULL, "
                    + "${ProductItem.QUANTITY_AVAILABLE} INTEGER NOT NULL, "
                    + "${ProductItem.CREATE_AT} INTEGER NOT NULL, "
                    + "${ProductItem.PRODUCT_ID} INTEGER NOT NULL, "
                    + "${ProductItem.CODE} TEXT NOT NULL, "
                    + "${ProductItem.NAME} TEXT NOT NULL)"
            )
    const val CREATE_PRODUCT_TABLE = (
            "CREATE TABLE ${Product.TABLE_NAME} ("
                    + "${Product.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${Product.CATEGORY_ID} INTEGER NOT NULL, "
                    + "${Product.CODE} TEXT NOT NULL, "
                    + "${Product.NAME} TEXT NOT NULL)"
            )
    const val CREATE_PRODUCT_ITEM_BILL_TABLE = (
            "CREATE TABLE ${ProductItemBill.TABLE_NAME} ("
                    + "${ProductItemBill.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${ProductItemBill.ITEM_ID} INTEGER NOT NULL, "
                    + "${ProductItemBill.QUANTITY} INTEGER NOT NULL, "
                    + "${ProductItemBill.BILL_ID} INTEGER NOT NULL)"
            )
    const val CREATE_BILL_TABLE = (
            "CREATE TABLE ${Bill.TABLE_NAME} ("
                    + "${Bill.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "${Bill.CUSTOMER_ID} INTEGER NOT NULL, "
                    + "${Bill.CREATE_AT} INTEGER NOT NULL)"
            )

    const val DROP_CATEGORY_TABLE = "DROP TABLE IF EXITS " + Category.TABLE_NAME
    const val DROP_CUSTOMER_TABLE = "DROP TABLE IF EXITS " + Customer.TABLE_NAME
    const val DROP_PRODUCT_COLOR_TABLE = "DROP TABLE IF EXITS " + ProductColor.TABLE_NAME
    const val DROP_PRODUCT_SIZE_TABLE = "DROP TABLE IF EXITS " + ProductSize.TABLE_NAME
    const val DROP_PRODUCT_UNIT_TABLE = "DROP TABLE IF EXITS " + ProductUnit.TABLE_NAME
    const val DROP_PRODUCT_ITEM_TABLE = "DROP TABLE IF EXITS " + ProductItem.TABLE_NAME
    const val DROP_PRODUCT_TABLE = "DROP TABLE IF EXITS " + Product.TABLE_NAME
    const val DROP_PRODUCT_ITEM_BILL_TABLE = "DROP TABLE IF EXITS " + ProductItemBill.TABLE_NAME
    const val DROP_BILL_TABLE = "DROP TABLE IF EXITS " + Bill.TABLE_NAME
}