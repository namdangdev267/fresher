package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class: Interface cho Repository chứa các phương thức tương tác với {@link MenuItem}
 * - Sử dụng khi: tạo Class để implement lại Interface này
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public interface IMenuItemRepository {
    /**
     * - Mục đích method: Cần lấy ra tất cả MenuItem
     *
     * @param callback Callback để xử lý List {@link MenuItem}
     * @created_by KhanhNQ on 12-Jan-21
     */
    void getAllMenuItems(IOnLoadedCallback<List<MenuItem>> callback);

    /**
     * - Mục đích method: Lấy ra các MenuItem theo id
     *
     * @param id id của item cần lấy ra
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    void getItemById(String id, IOnLoadedCallback<MenuItem> callback);

    /**
     * - Mục đích method: Lấy ra các MenuItem thuộc 1 group
     *
     * @param groupId id của group muốn lấy
     * @param callback Callback để xử lý List {@link MenuItem}
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    void getItemsByGroupId(String groupId, IOnLoadedCallback<List<MenuItem>> callback);

    /**
     * - Mục đích method: Thêm mới một MenuItem
     *
     * @param item thông tin của MenuItem cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    void addNewMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback);

    /**
     * - Mục đích method: Chỉnh sửa một MenuItem
     *
     * @param item thông tin của MenuItem cần chỉnh sửa
     * @param callback Callback để xử lý sau khi chỉnh sửa
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    void updateMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback);

    /**
     * - Mục đích method: Xóa mới một MenuItem
     *
     * @param item thông tin của MenuItem cần xóa
     * @param callback Callback để xử lý sau khi xóa
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    void deleteMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback);
}
