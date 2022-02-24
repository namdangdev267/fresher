package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.source.local.database.AppDatabase;

/**
 * - Mục đích Class: Data Access Object của object {@link CartItem}
 *
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class CartItemDao implements ICartItemDao {

    private final SQLiteDatabase database;

    private CartItemDao(AppDatabase appDatabase) {
        this.database = appDatabase.getWritableDatabase();
    }

    private static CartItemDao instance;

    /**
     * - Mục đích method: Singleton pattern để lấy instance của class
     *
     * @param appDatabase LocalDatabase
     * @return Instance của {@link CartItemDao}
     * @created_by KhanhNQ on 25-Jan-21
     */
    public static CartItemDao getInstance(AppDatabase appDatabase) {
        if (null == instance) {
            instance = new CartItemDao(appDatabase);
        }
        return instance;
    }

    @Override
    public List<CartItem> getAllItems() {
        Cursor cursor = database.query(CartItem.TABLE_NAME, null, null, null, null, null, null);
        List<CartItem> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new CartItem(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public List<CartItem> getItemsByCartId(String cartId) {
        String selection = CartItem.CART_ID + " =?";
        String[] selectionArg = {cartId};
        Cursor cursor = database.query(CartItem.TABLE_NAME, null, selection, selectionArg, null, null, null);
        List<CartItem> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new CartItem(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public CartItem getItemById(String id) {
        String selection = CartItem.ID + " =?";
        String[] selectionArg = {id};
        Cursor cursor = database.query(CartItem.TABLE_NAME, null, selection, selectionArg, null, null, null);
        cursor.moveToFirst();
        CartItem item = new CartItem(cursor);
        cursor.close();
        return item;
    }

    @Override
    public boolean addNewCartItem(CartItem item) {
        ContentValues contentValues = item.getContentValues();
        return database.insertWithOnConflict(CartItem.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE) > 0;
    }

    @Override
    public boolean updateCartItem(CartItem item) {
        ContentValues values = item.getContentValues();
        String selection = CartItem.ID + " =?";
        String[] selectionArg = {item.getId()};
        return database.update(CartItem.TABLE_NAME, values, selection, selectionArg) > 0;
    }

    @Override
    public boolean deleteCartItem(CartItem item) {
        String selection = CartItem.ID + " =?";
        String[] selectionArg = {item.getId()};
        return database.delete(CartItem.TABLE_NAME, selection, selectionArg) > 0;
    }
}
