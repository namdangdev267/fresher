package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.source.local.database.AppDatabase;

/**
 * - Mục đích Class: Class implement của Interface {@link IMenuGroupDao}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public class MenuGroupDao implements IMenuGroupDao {

    private final SQLiteDatabase database;

    private MenuGroupDao(AppDatabase appDatabase) {
        this.database = appDatabase.getWritableDatabase();
    }

    private static MenuGroupDao instance = null;

    /**
     * - Mục đích method: Singleton pattern để lấy instance của class
     *
     * @param appDatabase LocalDatabase
     * @return Instance của {@link MenuGroupDao}
     * @created_by KhanhNQ on 12-Jan-21
     */
    public static MenuGroupDao getInstance(AppDatabase appDatabase) {
        if (null == instance) {
            instance = new MenuGroupDao(appDatabase);
        }
        return instance;
    }

    /**
     * - Mục đích method: Lấy ra tất cả MenuGroup trong Local
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public List<MenuGroup> getAllGroups() {
        Cursor cursor = database.query(MenuGroup.TABLE_NAME, null, null, null, null, null, null);
        List<MenuGroup> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new MenuGroup(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    /**
     * - Mục đích method: Lấy ra MenuGroup với id cho trước
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public MenuGroup getGroupById(String groupId) {
        String selection = MenuGroup.ID + " =?";
        String[] selectionArg = {groupId};
        Cursor cursor = database.query(MenuGroup.TABLE_NAME, null, selection, selectionArg, null, null, null);
        cursor.moveToFirst();
        MenuGroup group = new MenuGroup(cursor);
        cursor.close();
        return group;
    }

    /**
     * - Mục đích method: Thêm mới một MenuGroup
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public boolean addNewMenuGroup(MenuGroup group) {
        ContentValues values = group.getContentValues();
        return database.insert(MenuGroup.TABLE_NAME, null, values) > 0;
    }

    @Override
    public boolean updateMenuGroup(MenuGroup group) {
        String selection = MenuGroup.ID + " =?";
        String[] selectionArg = {group.getId()};
        ContentValues values = group.getContentValues();
        return database.update(MenuGroup.TABLE_NAME, values, selection, selectionArg) > 0;
    }

    @Override
    public boolean deleteMenuGroup(MenuGroup group) {
        String selection = MenuGroup.ID + " =?";
        String[] selectionArg = {group.getId()};
        return database.delete(MenuGroup.TABLE_NAME, selection, selectionArg) > 0;
    }
}
