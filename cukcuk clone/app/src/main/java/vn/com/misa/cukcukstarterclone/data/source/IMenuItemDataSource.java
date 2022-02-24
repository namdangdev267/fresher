package vn.com.misa.cukcukstarterclone.data.source;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class: Interface chứa các phương thức xử lý data liên quan đến {@link MenuItem}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public interface IMenuItemDataSource {
    interface Local {
        /**
         * - Mục đích method: Lấy tất cả MenuItem trong Local
         *
         * @param callback Callback để xử lý sau khi lấy được dữ liệu
         * @created_by KhanhNQ on 12-Jan-21
         */
        void getAllMenuItems(IOnLoadedCallback<List<MenuItem>> callback);

        /**
         * - Mục đích method: Lấy ra Item theo id
         *
         * @param id id của MenuItem cần lấy ra thông tin
         * @param callback Callback để xử lý sau khi lấy được thông tin
         *
         * @created_by KhanhNQ on 27-Jan-21
         */
        void getItemById(String id, IOnLoadedCallback<MenuItem> callback);

        /**
         * - Mục đích method: Lấy ra các MenuItem theo groupId trong Local
         *
         * @param groupId  id của group cần lấy ra các MenuItem
         * @param callback Callback để xử lý sau khi lấy được dữ liệu
         * @created_by KhanhNQ on 12-Jan-21
         */
        void getItemsByGroupId(String groupId, IOnLoadedCallback<List<MenuItem>> callback);

        /**
         * - Mục đích method: Thêm mới một MenuItem vào Local
         *
         * @param item     thông tin của item cần thêm mới
         * @param callback Callback để xử lý sau khi thêm
         * @created_by KhanhNQ on 12-Jan-21
         */
        void addNewMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback);

        void updateMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback);

        void deleteMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback);
    }

    interface Remote {
    }
}
