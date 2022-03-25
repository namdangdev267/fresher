package com.misa.fresher.utils

import com.misa.fresher.data.model.product.*

object Queries {
    const val DATABASE_NAME: String = "fresher_database"
    const val DATABASE_VERSION: Int = 1

    const val CREATE_PRODUCT_MODEL_TABLE =
        "CREATE TABLE ${ProductModel.TABLE_NAME} ( " +
                "${ProductModel.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${ProductModel.CODE} TEXT NOT NULL, " +
                "${ProductModel.NAME} TEXT NOT NULL, " +
                "${ProductModel.CATEGORY} TEXT NOT NULL, " +
                "${ProductModel.DATE} INTEGER NOT NULL, " +
                "${ProductModel.IMAGE} TEXT NOT NULL )"

    const val CREATE_PRODUCT_ITEM_TABLE =
        "CREATE TABLE ${ProductItem.TABLE_NAME} ( " +
                "${ProductItem.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${ProductItem.MODEL_ID} INTEGER NOT NULL, " +
                "${ProductItem.CODE} TEXT NOT NULL, " +
                "${ProductItem.NAME} TEXT NOT NULL, " +
                "${ProductItem.SIZE} TEXT NOT NULL, " +
                "${ProductItem.COLOR} TEXT NOT NULL, " +
                "${ProductItem.PRICE} REAL NOT NULL, " +
                "${ProductItem.QUANTITY} INTEGER NOT NULL )"

    const val CREATE_PRODUCT_UNIT_TABLE =
        "CREATE TABLE ${ProductUnit.TABLE_NAME} ( " +
                "${ProductUnit.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${ProductUnit.NAME} TEXT NOT NULL, " +
                "${ProductUnit.VALUE} INTEGER NOT NULL )"

    const val CREATE_PRODUCT_BILL_TABLE =
        "CREATE TABLE ${ProductBill.TABLE_NAME} ( " +
                "${ProductBill.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${ProductBill.DATE} INTEGER NOT NULL, " +
                "${ProductBill.CUSTOMER} TEXT NOT NULL )"

    const val CREATE_MODEL_UNIT_TABLE =
        "CREATE TABLE ${ModelUnit.TABLE_NAME} ( " +
                "${ModelUnit.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${ModelUnit.MODEL_ID} INTEGER NOT NULL, " +
                "${ModelUnit.UNIT_ID} INTEGER NOT NULL )"

    const val CREATE_ITEM_BILL_TABLE =
        "CREATE TABLE ${ItemBill.TABLE_NAME} ( " +
                "${ItemBill.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${ItemBill.ITEM_ID} INTEGER NOT NULL, " +
                "${ItemBill.BILL_ID} INTEGER NOT NULL, " +
                "${ItemBill.AMOUNT} INTEGER NOT NULL )"


    const val DROP_PRODUCT_MODEL_TABLE = "DROP TABLE IF EXISTS ${ProductModel.TABLE_NAME}"
    const val DROP_PRODUCT_ITEM_TABLE = "DROP TABLE IF EXISTS ${ProductItem.TABLE_NAME}"
    const val DROP_PRODUCT_UNIT_TABLE = "DROP TABLE IF EXISTS ${ProductUnit.TABLE_NAME}"
    const val DROP_PRODUCT_BILL_TABLE = "DROP TABLE IF EXISTS ${ProductBill.TABLE_NAME}"
    const val DROP_MODEL_UNIT_TABLE = "DROP TABLE IF EXISTS ${ModelUnit.TABLE_NAME}"
    const val DROP_ITEM_BILL_TABLE = "DROP TABLE IF EXISTS ${ItemBill.TABLE_NAME}"
}