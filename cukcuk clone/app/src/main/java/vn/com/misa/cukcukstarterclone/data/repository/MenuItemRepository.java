package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.source.IMenuItemDataSource;

/**
 * - Mục đích Class: Repository để điều hướng xử lý dữ liệu
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public class MenuItemRepository implements IMenuItemRepository {
    private final IMenuItemDataSource.Local localDataSource;
    private final IMenuItemDataSource.Remote remoteDataSource;

    private static MenuItemRepository instance;

    private MenuItemRepository(IMenuItemDataSource.Local localDataSource, IMenuItemDataSource.Remote remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static MenuItemRepository getInstance(IMenuItemDataSource.Local localDataSource, IMenuItemDataSource.Remote remoteDataSource) {
        if (null == instance) {
            instance = new MenuItemRepository(localDataSource, remoteDataSource);
        }
        return instance;
    }

    /**
     * - Mục đích method: Cần lấy ra tất cả MenuItem
     *
     * @param callback Callback để xử lý List {@link MenuItem}
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void getAllMenuItems(IOnLoadedCallback<List<MenuItem>> callback) {
        localDataSource.getAllMenuItems(callback);
    }

    /**
     * - Mục đích method: Lấy ra các MenuItem theo id
     *
     * @param id id của item cần lấy ra
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    @Override
    public void getItemById(String id, IOnLoadedCallback<MenuItem> callback) {
        localDataSource.getItemById(id, callback);
    }

    /**
     * - Mục đích method: Lấy ra các MenuItem thuộc 1 group
     *
     * @param groupId id của group muốn lấy
     * @param callback Callback để xử lý List {@link MenuItem}
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void getItemsByGroupId(String groupId, IOnLoadedCallback<List<MenuItem>> callback) {
        localDataSource.getItemsByGroupId(groupId, callback);
    }

    /**
     * - Mục đích method: Thêm mới một MenuItem
     *
     * @param item thông tin của MenuItem cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void addNewMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback) {
        localDataSource.addNewMenuItem(item, callback);
    }

    /**
     * - Mục đích method: Chỉnh sửa một MenuItem
     *
     * @param item thông tin của MenuItem cần chỉnh sửa
     * @param callback Callback để xử lý sau khi chỉnh sửa
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void updateMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback) {
        localDataSource.updateMenuItem(item, callback);
    }

    /**
     * - Mục đích method: Xóa mới một MenuItem
     *
     * @param item thông tin của MenuItem cần xóa
     * @param callback Callback để xử lý sau khi xóa
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void deleteMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback) {
        localDataSource.deleteMenuItem(item, callback);
    }
}
