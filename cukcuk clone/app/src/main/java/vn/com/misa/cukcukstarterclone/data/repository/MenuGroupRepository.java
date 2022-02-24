package vn.com.misa.cukcukstarterclone.data.repository;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.source.IMenuGroupDataSource;

/**
 * - Mục đích Class: Repository để điều hướng xử lý dữ liệu liên quan đến {@link MenuGroup}
 *
 * @created_by KhanhNQ on 22-Jan-21.
 */
public class MenuGroupRepository implements IMenuGroupRepository {
    private final IMenuGroupDataSource.Local localDataSource;
    private final IMenuGroupDataSource.Remote remoteDataSource;

    private static MenuGroupRepository instance;

    private MenuGroupRepository(IMenuGroupDataSource.Local localDataSource, IMenuGroupDataSource.Remote remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static MenuGroupRepository getInstance(IMenuGroupDataSource.Local localDataSource, IMenuGroupDataSource.Remote remoteDataSource) {
        if (null == instance) {
            instance = new MenuGroupRepository(localDataSource, remoteDataSource);
        }
        return instance;
    }

    /**
     * - Mục đích method: Lấy tất cả MenuGroup trong Local
     *
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 22-Jan-21
     */
    @Override
    public void getAllMenuGroups(IOnLoadedCallback<List<MenuGroup>> callback) {
        localDataSource.getAllMenuGroups(callback);
    }

    /**
     * - Mục đích method: Lấy ra MenuGroup theo id trong Local
     *
     * @param groupId  id của group cần lấy ra
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 22-Jan-21
     */
    @Override
    public void getGroupById(String groupId, IOnLoadedCallback<MenuGroup> callback) {
        localDataSource.getGroupById(groupId, callback);
    }

    /**
     * - Mục đích method: Thêm mới một MenuGroup vào Local
     *
     * @param item     thông tin của item cần thêm mới
     * @param callback Callback để xử lý sau khi thêm
     * @created_by KhanhNQ on 22-Jan-21
     */
    @Override
    public void addNewMenuGroup(MenuGroup item, IOnLoadedCallback<Boolean> callback) {
        localDataSource.addNewMenuGroup(item, callback);
    }

    /**
     * - Mục đích method: Chỉnh sửa một MenuGroup vào Local
     *
     * @param group    thông tin của item cần chỉnh sửa
     * @param callback Callback để xử lý sau khi chỉnh sửa
     * @created_by KhanhNQ on 22-Jan-21
     */
    @Override
    public void updateMenuGroup(MenuGroup group, IOnLoadedCallback<Boolean> callback) {
        localDataSource.updateMenuGroup(group, callback);
    }

    @Override
    public void deleteMenuGroup(MenuGroup group, IOnLoadedCallback<Boolean> callback) {
        localDataSource.deleteMenuGroup(group, callback);
    }
}
