package com.misa.fresher

object Queries {
    const val CREATE_TABLE_PRODUCT = "CREATE TABLE PRODUCT (\n" +
            "    ID_PRODUCT    INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
            "                               NOT NULL,\n" +
            "    PRODUCT_NAME  VARCHAR (50) NOT NULL,\n" +
            "    PRODUCT_SKU   VARCHAR (20) NOT NULL,\n" +
            "    COLOR     VARCHAR(15)      NOT NULL,\n" +
            "    SIZE       VARCHAR(15)     NOT NULL,\n" +
            "    PRODUCT_PRICE REAL         NOT NULL\n" +
            ");"
    const val CREATE_TABLE_CUSTOMER = "CREATE TABLE CUSTOMER (\n" +
            "    CUSTOMER_ID      INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
            "    CUSTOMER_NAME    VARCHAR (50) NOT NULL,\n" +
            "    CUSTOMER_PHONE   VARCHAR (20) NOT NULL,\n" +
            "    CUSTOMER_ADDRESS VARCHAR (50) NOT NULL\n" +
            ");\n"
    const val CREATE_TABLE_BILL = "CREATE TABLE BILL (\n" +
            "    BILL_ID INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                    NOT NULL,\n" +
            "    CUSTOMER_ID INTEGER REFERENCES CUSTOMER (CUSTOMER_ID),\n" +
            "    TOTAL   REAL     DEFAULT (0) \n" +
            ");"
    const val CREATE_TABLE_CART = "CREATE TABLE CART (\n" +
            "    BILL_ID    INTEGER REFERENCES BILL (BILL_ID) \n" +
            "                       NOT NULL,\n" +
            "    PRODUCT_ID         REFERENCES PRODUCT (ID_PRODUCT) \n" +
            "                       NOT NULL,\n" +
            "    QUANTITY   INT     NOT NULL,\n" +
            "    PRICE      REAL    NOT NULL,\n" +
            "    PRIMARY KEY (\n" +
            "        BILL_ID,\n" +
            "        PRODUCT_ID\n" +
            "    )\n" +
            ");"
    const val INSERT_DEFAULT_PRODUCT="INSERT INTO PRODUCT(PRODUCT_NAME,PRODUCT_SKU,COLOR,SIZE,PRODUCT_PRICE)"+
            "VALUES"+
            "('T-Shirt','T1', 'red','L',100000),"+
            "('Sneaker','S1', 'red','XL',200000),"+
            "('Áo Phông','A1', 'red','L',75000),"+
            "('Áo Polo','A2', 'while','L',100000),"+
            "('Quần Âu','Q1', 'black','XL',150000),"+
            "('Tất','TA1', 'black','L',10000),"+
            "('Áo Sơ mi','ASM1', 'green','M',100000),"+
            "('Quần Sort','QS1', 'orange','L',100000);"

}