package kma.longhoang.beta.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import kma.longhoang.beta.dao.ProductDAO
import kma.longhoang.beta.model.CustomerModel
import kma.longhoang.beta.model.FilterProduct
import kma.longhoang.beta.model.ProductModel

@RequiresApi(Build.VERSION_CODES.P)
class AppDatabase private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        val createCustomerTB =
            "CREATE TABLE IF NOT EXISTS tb_customer (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,phone TEXT, UNIQUE(phone))"
        p0?.execSQL(createCustomerTB)
        val createProductTB =
            "CREATE TABLE IF NOT EXISTS tb_product (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,code TEXT,price FLOAT,style TEXT, color TEXT)"
        p0?.execSQL(createProductTB)
        val createOrderTB =
            "CREATE TABLE IF NOT EXISTS tb_order (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,code TEXT,color TEXT,price FLOAT,amount INTEGER, billId INTEGER,FOREIGN KEY(billId) REFERENCES tb_bill(id))"
        p0?.execSQL(createOrderTB)
        val createBillTB =
            "CREATE TABLE IF NOT EXISTS tb_bill (id INTEGER PRIMARY KEY,customerId INTEGER,date TEXT,state INTEGER, FOREIGN KEY(customerId) REFERENCES tb_customer(id))"
        p0?.execSQL(createBillTB)
        val listProduct = listOf<ProductModel>(
            ProductModel(
                "Áo len nam",
                "AL01",
                175000F,
                FilterProduct.Style.TSHIRT,
            ),
            ProductModel(
                "Áo thun cotton nam",
                "AT01",
                175000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.BLACK,
            ),
            ProductModel(
                "Áo len nữ",
                "AL02",
                175000F,
                FilterProduct.Style.TSHIRT,
            ),
            ProductModel(
                "Áo polo",
                "ACT01",
                135000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.WHITE
            ),
            ProductModel(
                "Váy ngắn",
                "VN01",
                120000F,
                FilterProduct.Style.SHORTDRESS,
                FilterProduct.Color.RED
            ),
            ProductModel(
                "Váy xòe",
                "VN02",
                146000F,
                FilterProduct.Style.SHORTDRESS,
                FilterProduct.Color.BLUE
            ),
            ProductModel(
                "Áo Somi nam",
                "ACT01",
                132000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.WHITE
            ),
            ProductModel(
                "Áo Sơ mi nữ",
                "ACT02",
                120000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.WHITE
            ),
            ProductModel(
                "Áo thun cotton nữ",
                "AT02",
                176000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.BLUE
            ),
            ProductModel(
                "Áo sơ mi cách điệu",
                "ACT03",
                200000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.RED
            ),
            ProductModel(
                "Quần Jean Nam",
                "QJ01",
                230000F,
                FilterProduct.Style.JEAN,
                FilterProduct.Color.BLUE
            ),
            ProductModel(
                "Quần Jean Nữ",
                "QJ02",
                270000F,
                FilterProduct.Style.JEAN,
                FilterProduct.Color.BLACK
            ),
            ProductModel(
                "Quần Jean nam rách",
                "QJ03",
                340000F,
                FilterProduct.Style.JEAN,
                FilterProduct.Color.WHITE
            ),
            ProductModel(
                "Áo thun cotton",
                "AT04",
                200000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.WHITE
            ),
            ProductModel(
                "Áo thun cotton A2",
                "AT05",
                150000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.BLACK
            ),
            ProductModel(
                "Quần nam",
                "Q01",
                220000F,
                FilterProduct.Style.SHORT,
                FilterProduct.Color.BLUE
            ),
            ProductModel(
                "Quần short nam",
                "Q02",
                250000F,
                FilterProduct.Style.SHORT,
                FilterProduct.Color.BLACK
            ),
            ProductModel(
                "Áo thun",
                "AT05",
                140000F,
                FilterProduct.Style.TSHIRT,
                FilterProduct.Color.RED
            ),
        )
        for (product in listProduct) {
            p0?.insert("tb_product", null, product.getContentValues())
        }
        val listCustomer = listOf<CustomerModel>(
            CustomerModel("A Nam", "09123456778"),
            CustomerModel("Long", "0967538265"),
            CustomerModel("Long 2", "01234567899"),
            CustomerModel("Kiên", "0987654321"),
            CustomerModel("Bảo", "2351575589659"),
            CustomerModel("Chính", "09123576565"),
        )
        for (customer in listCustomer) {
            p0?.insert("tb_customer", null, customer.getContentValues())
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS tb_customer")
        p0?.execSQL("DROP TABLE IF EXISTS tb_product")
        p0?.execSQL("DROP TABLE IF EXISTS tb_order")
        p0?.execSQL("DROP TABLE IF EXISTS tb_bill")
        onCreate(p0)
    }

    companion object {
        private const val DATABASE_NAME = "beta.db"
        private const val DATABASE_VERSION = 1
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (null == instance) {
                instance = AppDatabase(context)
            }
            return instance!!
        }
    }
}