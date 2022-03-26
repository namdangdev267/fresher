package com.misa.fresher.data

import com.misa.fresher.data.models.ItemBill
import com.misa.fresher.data.models.PackageProduct
import com.misa.fresher.data.models.Product

/**
 * Mục địch sử dụng:
 * Sử dụng khi:
 * @Author Truong Trung Kien
 * @date: 3/24/2022 9:38 PM
 **/
object Queries {
    const val DATABASE_NAME = "misa_fresher_database"
    const val DATABASE_VERSION = 6

    const val CREATE_PRODUCT_TABLE =
        "CREATE TABLE ${Product.TABLE_NAME} " +
                "(${Product.IDPRODUCT} TEXT PRIMARY KEY, " +
                "${Product.IMGPRODUCT} INTEGER, " +
                "${Product.NAMEPRODUCT} TEXT NOT NULL, " +
                "${Product.CODEPRODUCT} TEXT NOT NULL, " +
                "${Product.COLOR} TEXT NOT NULL, " +
                "${Product.CATEGORY} TEXT NOT NULL, " +
                "${Product.PRICEPRODUCT} INTEGER, " +
                "${Product.QUANTITY} INTEGER, " +
                "${Product.DATEARRIVAL} TEXT NOT NULL)"

    const val CREATE_PACKAGE_PRODUCT_TABLE =
        "CREATE TABLE ${PackageProduct.TABLE_NAME} " +
                "(${PackageProduct.ID} TEXT PRIMARY KEY, " +
                "${PackageProduct.NAME} TEXT NOT NULL, " +
                "${PackageProduct.CODE} TEXT, " +
                "${PackageProduct.ID_ITEM_PRODUCT} TEXT NOT NULL, " +
                "${PackageProduct.ID_ITEM_BILL} TEXT NOT NULL, " +
                "${PackageProduct.QUANTITY} INTEGER)"

    const val CREATE_ITEM_BILL_TABLE =
        "CREATE TABLE ${ItemBill.TABLE_NAME} " +
                "(${ItemBill.ID} TEXT PRIMARY KEY, " +
                "${ItemBill.ID_INFOR_SHIP} TEXT, " +
                "${ItemBill.STATUS} TEXT NOT NULL, " +
                "${ItemBill.CREATE_DAY} TEXT, " +
                "${ItemBill.BILL_PRICE} TEXT)"

    const val DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS ${Product.TABLE_NAME}"
    const val DROP_PACKAGE_PRODUCT_TABLE = "DROP TABLE IF EXISTS ${PackageProduct.TABLE_NAME}"
    const val DROP_ITEM_BILL_TABLE = "DROP TABLE IF EXISTS ${ItemBill.TABLE_NAME}"
}
