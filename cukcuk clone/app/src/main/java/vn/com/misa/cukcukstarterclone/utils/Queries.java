package vn.com.misa.cukcukstarterclone.utils;

import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.model.Order;

/**
 * - Mục đích Class: Chứa các query để tương tác với database
 *
 * @created_by KhanhNQ on 07-Jan-21.
 */
public class Queries {
    public static final String DATABASE_NAME = "cukcuk_database";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_MENU_GROUP_TABLE =
            "CREATE TABLE " + MenuGroup.TABLE_NAME + " (" +
                    MenuGroup.ID + " TEXT PRIMARY KEY, "
                    + MenuGroup.TITLE + " TEXT NOT NULL, "
                    + MenuGroup.ICON_URL + " TEXT NOT NULL"
                    + ")";

    public static final String CREATE_MENU_TABLE =
            "CREATE TABLE " + MenuItem.TABLE_NAME + " (" +
                    MenuItem.ID + " TEXT PRIMARY KEY, "
                    + MenuItem.PRICE + " REAL NOT NULL, "
                    + MenuItem.IMAGE_URL + " TEXT NOT NULL, "
                    + MenuItem.NAME + " TEXT NOT NULL, "
                    + MenuItem.UNIT + " TEXT NOT NULL, "
                    + MenuItem.GROUP_ID + " TEXT NOT NULL"
                    + ")";


    public static final String CREATE_CART_TABLE =
            "CREATE TABLE " + Cart.TABLE_NAME + " ("
                    + Cart.ID + " TEXT PRIMARY KEY, "
                    + Cart.TITLE + " TEXT NOT NULL, "
                    + Cart.TOTAL_AMOUNT + " REAL, "
                    + Cart.TOTAL_PRICE + " REAL, "
                    + Cart.DISCOUNT + " REAL, "
                    + Cart.TABLE_NUMBER + " INTEGER, "
                    + Cart.TYPE + " INTEGER, "
                    + Cart.STATUS + " INTEGER, "
                    + Cart.CREATED_AT + " TEXT"
                    + ")";

    public static final String CREATE_CART_ITEM_TABLE =
            "CREATE TABLE " + CartItem.TABLE_NAME + " ("
                    + CartItem.ID + " TEXT PRIMARY KEY, "
                    + CartItem.QUANTITY + " INTEGER NOT NULL, "
                    + CartItem.AMOUNT + " REAL, "
                    + CartItem.PRICE + " REAL, "
                    + CartItem.DETAILS + " TEXT, "
                    + CartItem.PRODUCT_ID + " TEXT NOT NULL, "
                    + CartItem.CART_ID + " TEXT NOT NULL"
                    + ")";

    public static final String CREATE_ORDER_TABLE =
            "CREATE TABLE " + Order.TABLE_NAME + " ("
                    + Order.ID + " TEXT PRIMARY KEY, "
                    + Order.TYPE + " INTEGER, "
                    + Order.CUSTOMER_PAYMENT + " REAL, "
                    + Order.CART_ID + " TEXT NOT NULL, "
                    + Order.CREATED_AT + " TEXT"
                    + ")";

    public static final String DROP_MENU_GROUP_TABLE = "DROP TABLE IF EXITS " + MenuGroup.TABLE_NAME;

    public static final String DROP_MENU_TABLE = "DROP TABLE IF EXITS " + MenuItem.TABLE_NAME;

    public static final String DROP_CART_TABLE = "DROP TABLE IF EXITS " + Cart.TABLE_NAME;

    public static final String DROP_CART_ITEM_TABLE = "DROP TABLE IF EXITS " + CartItem.TABLE_NAME;

    public static final String DROP_ORDER_TABLE = "DROP TABLE IF EXITS " + Order.TABLE_NAME;

}
