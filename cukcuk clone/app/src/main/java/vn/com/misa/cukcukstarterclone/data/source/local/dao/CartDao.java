package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.Cart.CartStatus;
import vn.com.misa.cukcukstarterclone.data.source.local.database.AppDatabase;

/**
 * - Mục đích Class: Class implement của Interface {@link ICartDao}
 *
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class CartDao implements ICartDao {

    private final SQLiteDatabase database;

    private CartDao(AppDatabase appDatabase) {
        this.database = appDatabase.getWritableDatabase();
    }

    private static CartDao instance = null;

    /**
     * - Mục đích method: Singleton pattern để lấy instance của class
     *
     * @param appDatabase LocalDatabase
     * @return Instance của {@link CartDao}
     * @created_by KhanhNQ on 25-Jan-21
     */
    public static CartDao getInstance(AppDatabase appDatabase) {
        if (null == instance) {
            instance = new CartDao(appDatabase);
        }
        return instance;
    }

    @Override
    public List<Cart> getAllCarts() {
        String selection = Cart.STATUS + " =?";
        String[] selectionArg = {String.valueOf(CartStatus.WAITING.getValue())};
        Cursor cursor = database.query(Cart.TABLE_NAME, null, selection, selectionArg, null, null, null);
        List<Cart> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new Cart(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public Cart getCartById(String cartId) {
        String selection = Cart.ID + " =?";
        String[] selectionArg = {cartId};
        Cursor cursor = database.query(Cart.TABLE_NAME, null, selection, selectionArg, null, null, null);
        cursor.moveToFirst();
        Cart cart = new Cart(cursor);
        cursor.close();
        return cart;
    }

    @Override
    public boolean addNewCart(Cart cart) {
        ContentValues values = cart.getContentValues();
        return database.insertWithOnConflict(Cart.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE) > 0;
    }

    @Override
    public boolean updateCart(Cart cart) {
        ContentValues values = cart.getContentValues();
        String selection = Cart.ID + " =?";
        String[] selectionArg = {cart.getId()};
        return database.update(Cart.TABLE_NAME, values, selection, selectionArg) > 0;
    }

    @Override
    public boolean deleteCart(Cart cart) {
        String selection = Cart.ID + " =?";
        String[] selectionArg = {cart.getId()};
        return database.delete(Cart.TABLE_NAME, selection, selectionArg) > 0;
    }
}
