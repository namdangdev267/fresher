package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.model.Order;
import vn.com.misa.cukcukstarterclone.data.source.local.database.AppDatabase;

/**
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public class OrderDao implements IOrderDao {

    private final SQLiteDatabase database;

    private OrderDao(AppDatabase appDatabase) {
        this.database = appDatabase.getWritableDatabase();
    }

    private static OrderDao instance = null;

    public static OrderDao getInstance(AppDatabase appDatabase) {
        if (null == instance) {
            instance = new OrderDao(appDatabase);
        }
        return instance;
    }

    @Override
    public List<Order> getAllOrders() {
        Cursor cursor = database.query(Order.TABLE_NAME, null, null, null, null, null, null);
        List<Order> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new Order(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        String from = date.split("to")[0];
        String to = date.split("to")[1] + " 23:59:59";
        String selection = Order.CREATED_AT + " >= ? AND " + Order.CREATED_AT + " <= ?";
        String[] selectionArg = {from, to};
        Cursor cursor = database.query(Order.TABLE_NAME, null, selection, selectionArg, null, null, null);
        List<Order> orders = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                orders.add(new Order(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return orders;
    }

    @Override
    public Order getOrderById(String id) {
        String selection = Order.ID + " =?";
        String[] selectionArg = {id};
        Cursor cursor = database.query(Order.TABLE_NAME, null, selection, selectionArg, null, null, null);
        cursor.moveToFirst();
        Order item = new Order(cursor);
        cursor.close();
        return item;
    }

    @Override
    public boolean addNewOrder(Order newOrder) {
        ContentValues contentValues = newOrder.getContentValues();
        return database.insert(Order.TABLE_NAME, null, contentValues) > 0;
    }
}
