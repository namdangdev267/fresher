package com.misa.fresher.utils

import com.misa.fresher.data.model.*

object Queries {
    const val DATABASE_NAME: String = "fresher_database"
    const val DATABASE_VERSION: Int = 16
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
            "${Customer.NAME} TEXT, " +
            "${Customer.NUMBER} TEXT, " +
            "${Customer.ADDRESS} TEXT)"

    const val CREATE_BILL_TABLE = "CREATE TABLE ${Bill.TABLE_NAME} (" +
            "${Bill.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${Bill.ID_CUSTOMER} INTEGER REFERENCES ${Customer.TABLE_NAME}(${Customer.ID}), " +
            "${Bill.DATE} TEXT, " +
            "${Bill.TOTAL_PRICE} REAL)"

    const val CREATE_SELECTED_PRODUCT_TABLE = "CREATE TABLE ${SelectedProducts.TABLE_NAME} (" +
            "${SelectedProducts.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "${SelectedProducts.ID_PRODUCT} INTEGER REFERENCES ${Products.TABLE_NAME}(${Products.ID}), " +
            "${SelectedProducts.ID_BILL} INTEGER REFERENCES ${Bill.TABLE_NAME}(${Bill.ID}), " +
            "${SelectedProducts.AMONUT} INTEGER NOT NULL)"

    const val DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS ${Products.TABLE_NAME}"
    const val DROP_CUSTOMER_TABLE = "DROP TABLE IF EXISTS ${Customer.TABLE_NAME}"
    const val DROP_BILL_TABLE = "DROP TABLE IF EXISTS ${Bill.TABLE_NAME}"
    const val DROP_SELECTED_PRODUCT_TABLE = "DROP TABLE IF EXISTS ${SelectedProducts.TABLE_NAME}"

    const val INSERT_DEFAULT_PRODUCT =
        "INSERT INTO ${Products.TABLE_NAME} (${Products.CODE},${Products.NAME},${Products.PRICE},${Products.IMG},${Products.COLOR},${Products.SIZE})" +
                "VALUES " +
                "('ABC01-D-L', 'Ao dai (Do/L)', 1020.0, 2131230883, 'Do', 'L')," +
                "('ABC02-D-L', 'Ao dai (Do/S)', 21020.0, 2131230883, 'Do', 'S')," +
                "('ABC03-D-L', 'Ao ngan (Do/M)', 13020.0, 2131230883, 'Do', 'M')," +
                "('ABC04-D-L', 'Ao dai (Do/XL)', 122.0, 2131230883, 'Do', 'XL')," +
                "('ABC05-D-L', 'Quan dai (Do/L)', 11.0, 2131230883, 'Do', 'L')," +
                "('ABC06-D-L', 'Quan dai (XANH/L)', 3442.0, 2131230883, 'XANH', 'L')," +
                "('ABC07-D-L', 'Quan dai (XAM/XL)', 111.0, 2131230883, 'XAM', 'XL')," +
                "('ABC08-D-L', 'Quan dai (TRANG/L)', 80.0, 2131230883, 'TRANG', 'L')," +
                "('ABC09-D-L', 'Ao ngan (Do/L)', 324.0, 2131230883, 'Do', 'L')," +
                "('ABC10-D-L', 'Ao dai (Do/M)', 2331.0, 2131230883, 'Do', 'M')," +
                "('ABC11-D-L', 'Ao dai (XANH/L)', 1123.0, 2131230883, 'XANH', 'L')," +
                "('ABC12-D-L', 'Ao dai (XAM/L)', 2221.0, 2131230883, 'XAM', 'L')," +
                "('ABC13-D-L', 'Ao dai (TRANG/L)', 33321.0, 2131230883, 'TRANG', 'L')," +
                "('ABC14-D-L', 'Ao dai (TIM/L)', 11343.0, 2131230883, 'TIM', 'L')," +
                "('ABC15-D-L', 'Ao dai (XANH/M)', 1124.0, 2131230883, 'XANH', 'M')," +
                "('ABC16-D-L', 'Ao dai (TRANG/XL)', 1122.0, 2131230883, 'TRANG', 'XL')," +
                "('ABC17-D-L', 'Ao dai (XAM/L)', 6750.0, 2131230883, 'XAM', 'L');"

    const val INSERT_DEFAUL_CUSTOMER =
        "INSERT INTO ${Customer.TABLE_NAME} (${Customer.NAME},${Customer.NUMBER},${Customer.ADDRESS})" +
                "VALUES" +
                "('Nguyen Thai Bao','093123653','Ha Noi')," +
                "('HD Minh','0933123663','Ha Noi')," +
                "('HT Long','0931232663','Ha Noi')," +
                "('NC Chinh','092123663','Ha Noi')," +
                "('TT Kien','083123663','Ha Noi');"

    const val INSERT_DEFAULT_BILL =
        "INSERT INTO ${Bill.TABLE_NAME} (${Bill.ID_CUSTOMER},${Bill.TOTAL_PRICE},${Bill.DATE})" +
                "VALUES" +
                "(1,33750.0,'14/3/2022')," +
                "(2,33750.0,'15/3/2022')," +
                "(3,33750.0,'23/3/2022')," +
                "(4,33750.0,'24/3/2022')," +
                "(5,33750.0,'25/3/2022')," +
                "(1,33750.0,'25/3/2022');"

    const val INSERT_DEFAULT_SELECTEDPRODUCT =
        "INSERT INTO ${SelectedProducts.TABLE_NAME} (${SelectedProducts.ID_PRODUCT},${SelectedProducts.ID_BILL},${SelectedProducts.AMONUT})" +
                "VALUES" +
                "(5,1,5)," +
                "(5,2,5)," +
                "(5,3,5)," +
                "(5,4,5)," +
                "(5,5,5)," +
                "(5,6,5);"

    const val SELECT_LASTEST_BILL_ID =
        "SELECT * FROM ${Bill.TABLE_NAME} ORDER BY ${Bill.ID} DESC LIMIT 1"
    const val QUERY_BILL = "SELECT b.id,cus.id AS id_cus ,cus.name AS name_cus," +
            "cus.number AS number_cus,cus.address AS address_cus,b.date,b.totalPrice" +
            "FROM bill b, customer cus " +
            "WHERE b.idCustomer = cus.id"

}