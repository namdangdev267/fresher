package com.misa.fresher.data

import com.misa.fresher.models.InfoShip
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.ItemBillDetail
import com.misa.fresher.models.ItemProduct

object Queries {
    const val DATABASE_NAME = "database"
    const val DATABASE_VERSION = 7

    const val CREATE_BILL_STATUS_TABLE = "CREATE TABLE " + "BILL_STATUS_TABLE" + " (" +
            "id" + " TEXT PRIMARY KEY, " +
            "name" + " TEXT NOT NULL " + ")"

    const val CREATE_COLOR_TABLE = "CREATE TABLE " + "COLOR_TABLE" + " (" +
            "id" + " TEXT PRIMARY KEY, " +
            "name" + " TEXT NOT NULL " + ")"

    const val CREATE_CATEGORY_TABLE = "CREATE TABLE " + "CATEGORY_TABLE" + " (" +
            "id" + " TEXT PRIMARY KEY, " +
            "name" + " TEXT NOT NULL " + ")"

    const val CREATE_INFO_SHIP_TABLE = "CREATE TABLE ${InfoShip.TABLE_NAME}  (" +
            "${InfoShip.ID} TEXT PRIMARY KEY, " +
            "${InfoShip.RECEIVER} TEXT, " +
            "${InfoShip.TEL} TEXT, " +
            "${InfoShip.ADDRESS} TEXT, " +
            "${InfoShip.AREA} TEXT )"

    const val CREATE_ITEM_PRODUCT_TABLE = "CREATE TABLE ${ItemProduct.TABLE_NAME}  (" +
            "${ItemProduct.ID} TEXT PRIMARY KEY, " +
            "${ItemProduct.NAME} TEXT NOT NULL, " +
            "${ItemProduct.PRICE} REAL, " +
            "${ItemProduct.CODE} TEXT, " +
            "${ItemProduct.COLOR} TEXT, " +
            "${ItemProduct.CATEGORY} TEXT, " +
            "${ItemProduct.AVAILABLE_QUANTITY} INTEGER, " +
            "${ItemProduct.DATE_ARRIVAL} TEXT )"

    const val CREATE_ITEM_BILL_DETAIL_TABLE = "CREATE TABLE " + ItemBillDetail.TABLE_NAME + " (" +
            "${ItemBillDetail.ID} TEXT PRIMARY KEY, " +
            "${ItemBillDetail.NAME} TEXT NOT NULL, " +
            "${ItemBillDetail.ID_ITEM_PRODUCT} TEXT NOT NULL, " +
            "${ItemBillDetail.ID_ITEM_BILL} TEXT NOT NULL, " +
            "${ItemBillDetail.CODE} TEXT, " +
            "${ItemBillDetail.QUANTITY} INTEGER " +
            ")"

    const val CREATE_ITEM_BILL_TABLE = "CREATE TABLE " + ItemBill.TABLE_NAME + " (" +
            "${ItemBill.ID} TEXT PRIMARY KEY, " +
            "${ItemBill.ID_INFO_SHIP} REAL, " +
            "${ItemBill.STATUS} TEXT NOT NULL, " +
            "${ItemBill.CREATE_DAY} TEXT, " +
            "${ItemBill.BILL_PRICE} REAL "+
            ")"

    const val DROP_BILL_STATUS_TABLE = "DROP TABLE IF EXISTS " + "BILL_STATUS_TABLE"
    const val DROP_COLOR_TABLE = "DROP TABLE IF EXISTS " + "COLOR_TABLE"
    const val DROP_CATEGORY_TABLE = "DROP TABLE IF EXISTS " + "CATEGORY_TABLE"
    const val DROP_INFO_SHIP_TABLE = "DROP TABLE IF EXISTS " + "INFO_SHIP_TABLE"
    const val DROP_ITEM_PRODUCT_TABLE = "DROP TABLE IF EXISTS " + ItemProduct.TABLE_NAME
    const val DROP_ITEM_BILL_DETAIL_TABLE = "DROP TABLE IF EXISTS " + ItemBillDetail.TABLE_NAME
    const val DROP_ITEM_BILL_TABLE = "DROP TABLE IF EXISTS " + ItemBill.TABLE_NAME


}