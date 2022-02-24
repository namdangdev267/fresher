package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.source.local.database.AppDatabase;

/**
 * - Mục đích Class: Data Access Object của object {@link MenuItem}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public class MenuItemDao implements IMenuItemDao {

    private final SQLiteDatabase database;

    private MenuItemDao(AppDatabase appDatabase) {
        this.database = appDatabase.getWritableDatabase();
    }

    private static MenuItemDao instance;

    /**
     * - Mục đích method: Singleton pattern để lấy instance của class
     *
     * @param appDatabase LocalDatabase
     * @return Instance của {@link MenuItem}
     * @created_by KhanhNQ on 12-Jan-21
     */
    public static MenuItemDao getInstance(AppDatabase appDatabase) {
        if (null == instance) {
            instance = new MenuItemDao(appDatabase);
        }
        return instance;
    }

    /**
     * - Mục đích method: Lấy ra tất cả MenuItem trong Local
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public List<MenuItem> getAllItems() {
        Cursor cursor = database.query(MenuItem.TABLE_NAME, null, null, null, null, null, null);
        List<MenuItem> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new MenuItem(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    /**
     * - Mục đích method: Lấy ra các MenuItem với groupId cho trước
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public List<MenuItem> getItemsByGroupId(String groupId) {
        String selection = MenuItem.GROUP_ID + " =?";
        String[] selectionArg = {groupId};
        Cursor cursor = database.query(MenuItem.TABLE_NAME, null, selection, selectionArg, null, null, null);
        List<MenuItem> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new MenuItem(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    /**
     * - Mục đích method: Lấy ra MenuItem với id cho trước
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public MenuItem getItemById(String id) {
        String selection = MenuItem.ID + " =?";
        String[] selectionArg = {id};
        Cursor cursor = database.query(MenuItem.TABLE_NAME, null, selection, selectionArg, null, null, null);
        cursor.moveToFirst();
        MenuItem item = new MenuItem(cursor);
        cursor.close();
        return item;
    }

    /**
     * - Mục đích method: Thêm mới một MenuItem
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public boolean addNewMenuItem(MenuItem item) {
        ContentValues contentValues = item.getContentValues();
        return database.insert(MenuItem.TABLE_NAME, null, contentValues) > 0;
    }

    @Override
    public boolean updateMenuItem(MenuItem item) {
        ContentValues values = item.getContentValues();
        String selection = MenuItem.ID + " =?";
        String[] selectionArg = {item.getId()};
        return database.update(MenuItem.TABLE_NAME, values, selection, selectionArg) > 0;
    }

    @Override
    public boolean deleteItem(MenuItem item) {
        String selection = MenuItem.ID + " =?";
        String[] selectionArg = {item.getId()};
        return database.delete(MenuItem.TABLE_NAME, selection, selectionArg) > 0;
    }
}
