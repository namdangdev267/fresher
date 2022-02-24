package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;

/**
 * - Mục đích Class: Interface cho Repository chứa các phương thức tương tác với {@link MenuGroup}
 * - Sử dụng khi: tạo Class để implement lại Interface này
 *
 * @created_by KhanhNQ on 22-Jan-21.
 */
public interface IMenuGroupRepository {
    /**
     * - Mục đích method: Lấy tất cả MenuGroup trong Local
     *
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 22-Jan-21
     */
    void getAllMenuGroups(IOnLoadedCallback<List<MenuGroup>> callback);

    /**
     * - Mục đích method: Lấy ra MenuGroup theo id trong Local
     *
     * @param groupId  id của group cần lấy ra
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 22-Jan-21
     */
    void getGroupById(String groupId, IOnLoadedCallback<MenuGroup> callback);

    /**
     * - Mục đích method: Thêm mới một MenuGroup vào Local
     *
     * @param item     thông tin của item cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     * @created_by KhanhNQ on 22-Jan-21
     */
    void addNewMenuGroup(MenuGroup item, IOnLoadedCallback<Boolean> callback);

    /**
     * - Mục đích method: Chỉnh sửa một MenuGroup vào Local
     *
     * @param group    thông tin của item cần chỉnh sửa
     * @param callback Callback để xử lý sau khi chỉnh sửa
     * @created_by KhanhNQ on 22-Jan-21
     */
    void updateMenuGroup(MenuGroup group, IOnLoadedCallback<Boolean> callback);

    void deleteMenuGroup(MenuGroup group, IOnLoadedCallback<Boolean> callback);
}
