package com.misa.fresher.utils

import com.misa.fresher.data.model.*

object Queries {
    const val DATABASE_NAME: String = "fresher_database"
    const val DATABASE_VERSION: Int = 6
    const val CREATE_PRODUCT_TABLE = "CREATE TABLE ${Products.TABLE_NAME} (" +
            "${Products.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${Products.CODE} TEXT NOT NULL, " +
            "${Products.NAME} TEXT NOT NULL, " +
            "${Products.PRICE} TEXT NOT NULL, " +
            "${Products.IMG} TEXT NOT NULL, " +
            "${Products.COLOR} TEXT NOT NULL, " +
            "${Products.SIZE} TEXT NOT NULL)"

    const val CREATE_CUSTOMER_TABLE = "CREATE TABLE ${Customer.TABLE_NAME} (" +
            "${Customer.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${Customer.NAME} TEXT NOT NULL, " +
            "${Customer.NUMBER} TEXT NOT NULL, " +
            "${Customer.ADDRESS} TEXT NOT NULL)"

    const val CREATE_BILL_TABLE = "CREATE TABLE ${Bill.TABLE_NAME} (" +
            "${Bill.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${Bill.ID_CUSTOMER} INTEGER REFERENCES ${Customer.TABLE_NAME}(${Customer.ID}), " +
            "${Bill.DATE} TEXT NOT NULL, " +
            "${Bill.TOTAL_PRICE} REAL)"

    const val CREATE_SELECTED_PRODUCT_TABLE = "CREATE TABLE ${SelectedProducts.TABLE_NAME} (" +
            "${SelectedProducts.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${SelectedProducts.ID_PRODUCT} INTEGER REFERENCES ${Products.TABLE_NAME}(${Products.ID}), " +
            "${SelectedProducts.ID_BILL} INTEGER REFERENCES ${Bill.TABLE_NAME}(${Bill.ID}), " +
            "${SelectedProducts.AMONUT} INTEGER NOT NULL)"
    const val INSERT_VALUE_PRODUCT = "INSERT"

    const val DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS ${Products.TABLE_NAME}"
    const val DROP_CUSTOMER_TABLE = "DROP TABLE IF EXISTS ${Customer.TABLE_NAME}"
    const val DROP_BILL_TABLE = "DROP TABLE IF EXISTS ${Bill.TABLE_NAME}"
    const val DROP_SELECTED_PRODUCT_TABLE = "DROP TABLE IF EXISTS ${SelectedProducts.TABLE_NAME}"
}