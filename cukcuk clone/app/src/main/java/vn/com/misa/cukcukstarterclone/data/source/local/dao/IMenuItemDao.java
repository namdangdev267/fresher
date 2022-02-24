package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class: Interface chứa các phương thức tương tác với Local Database liên quan đến {@link MenuItem}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public interface IMenuItemDao {
    /**
     * - Mục đích method: Lấy ra tất cả MenuItem trong Local
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    List<MenuItem> getAllItems();

    /**
     * - Mục đích method: Lấy ra các MenuItem với groupId cho trước
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    List<MenuItem> getItemsByGroupId(String groupId);

    /**
     * - Mục đích method: Lấy ra MenuItem với id cho trước
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    MenuItem getItemById(String id);

    /**
     * - Mục đích method: Thêm mới một MenuItem
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    boolean addNewMenuItem(MenuItem item);

    boolean updateMenuItem(MenuItem item);

    boolean deleteItem(MenuItem item);
}
