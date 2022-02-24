package vn.com.misa.cukcukstarterclone.data.source.local.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static vn.com.misa.cukcukstarterclone.utils.Queries.CREATE_CART_ITEM_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.CREATE_CART_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.CREATE_MENU_GROUP_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.CREATE_MENU_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.CREATE_ORDER_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.DATABASE_NAME;
import static vn.com.misa.cukcukstarterclone.utils.Queries.DATABASE_VERSION;
import static vn.com.misa.cukcukstarterclone.utils.Queries.DROP_CART_ITEM_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.DROP_CART_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.DROP_MENU_GROUP_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.DROP_MENU_TABLE;
import static vn.com.misa.cukcukstarterclone.utils.Queries.DROP_ORDER_TABLE;

/**
 * - Mục đích Class:
 * - Sử dụng khi:
 *
 * @created_by KhanhNQ on 07-Jan-21.
 */
public class AppDatabase extends SQLiteOpenHelper {

    public static volatile AppDatabase instance = null;

    public static AppDatabase getInstance(Context context) {
        if (null == instance) {
            instance = new AppDatabase(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        return instance;
    }

    private AppDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MENU_GROUP_TABLE);
        db.execSQL(CREATE_MENU_TABLE);
        db.execSQL(CREATE_CART_TABLE);
        db.execSQL(CREATE_CART_ITEM_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_MENU_GROUP_TABLE);
        db.execSQL(DROP_MENU_TABLE);
        db.execSQL(DROP_CART_TABLE);
        db.execSQL(DROP_CART_ITEM_TABLE);
        db.execSQL(DROP_ORDER_TABLE);

        onCreate(db);
    }
}
