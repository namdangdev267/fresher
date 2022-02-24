package vn.com.misa.cukcukstarterclone.data.source.local;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.LoadingAsyncTask;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.source.IMenuGroupDataSource;
import vn.com.misa.cukcukstarterclone.data.source.Unit;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.IMenuGroupDao;

/**
 * - Mục đích Class: Sử dụng MenuGroupDao để tương tương tác với dữ liệu trong Local
 * - Sử dụng khi: được gọi từ Repository
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public class MenuGroupLocalDataSource implements IMenuGroupDataSource.Local {
    private final IMenuGroupDao menuGroupDao;

    private static MenuGroupLocalDataSource instance;

    private MenuGroupLocalDataSource(IMenuGroupDao menuGroupDao) {
        this.menuGroupDao = menuGroupDao;
    }

    public static MenuGroupLocalDataSource getInstance(IMenuGroupDao menuGroupDao) {
        if (null == instance) {
            instance = new MenuGroupLocalDataSource(menuGroupDao);
        }
        return instance;
    }

    /**
     * - Mục đích method: Lấy tất cả MenuGroup trong Local
     *
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void getAllMenuGroups(IOnLoadedCallback<List<MenuGroup>> callback) {
        new LoadingAsyncTask<Unit, List<MenuGroup>>(callback, param -> menuGroupDao.getAllGroups()).execute(new Unit());
    }

    /**
     * - Mục đích method: Lấy ra MenuGroup theo id trong Local
     *
     * @param groupId  id của group cần lấy ra
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void getGroupById(String groupId, IOnLoadedCallback<MenuGroup> callback) {
        new LoadingAsyncTask<String, MenuGroup>(callback, param -> menuGroupDao.getGroupById(groupId)).execute(groupId);
    }

    /**
     * - Mục đích method: Thêm mới một MenuGroup vào Local
     *
     * @param item     thông tin của item cần thêm mới
     * @param callback Callback để xử lý sau khi lấy được dữ liệu
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void addNewMenuGroup(MenuGroup item, IOnLoadedCallback<Boolean> callback) {
        new LoadingAsyncTask<MenuGroup, Boolean>(callback, param -> menuGroupDao.addNewMenuGroup(item)).execute(item);
    }

    @Override
    public void updateMenuGroup(MenuGroup group, IOnLoadedCallback<Boolean> callback) {
        new LoadingAsyncTask<MenuGroup, Boolean>(callback, param -> menuGroupDao.updateMenuGroup(group)).execute(group);
    }

    @Override
    public void deleteMenuGroup(MenuGroup group, IOnLoadedCallback<Boolean> callback) {
        new LoadingAsyncTask<MenuGroup, Boolean>(callback, param -> menuGroupDao.deleteMenuGroup(group)).execute(group);
    }
}
