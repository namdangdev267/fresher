package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;

/**
 * - Mục đích Class: Interface chứa các phương thức tương tác với Local Database liên quan đến {@link MenuGroup}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public interface IMenuGroupDao {
    /**
     * - Mục đích method: Lấy ra tất cả MenuGroup trong Local
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    List<MenuGroup> getAllGroups();

    /**
     * - Mục đích method: Lấy ra MenuGroup với id cho trước
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    MenuGroup getGroupById(String groupId);

    /**
     * - Mục đích method: Thêm mới một MenuGroup
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    boolean addNewMenuGroup(MenuGroup group);

    boolean updateMenuGroup(MenuGroup group);

    boolean deleteMenuGroup(MenuGroup group);
}
