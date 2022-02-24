package vn.com.misa.cukcukstarterclone.data.source.local;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.LoadingAsyncTask;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.source.IMenuItemDataSource;
import vn.com.misa.cukcukstarterclone.data.source.Unit;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.IMenuItemDao;

/**
 * - Mục đích Class: LocalDataSource để tương tác với database liên quan tới {@link MenuItem}
 *
 * @created_by KhanhNQ on 09-Jan-21.
 */
public class MenuItemLocalDataSource implements IMenuItemDataSource.Local {

    private static MenuItemLocalDataSource instance = null;

    private final IMenuItemDao menuItemDao;

    private MenuItemLocalDataSource(IMenuItemDao menuItemDao) {
        this.menuItemDao = menuItemDao;
    }

    /**
     * - Mục đích method: Singleton pattern để lấy instance của class
     *
     * @param menuItemDao DAO của {@link MenuItem}
     * @return Instance của {@link MenuItemLocalDataSource}
     * @created_by KhanhNQ on 12-Jan-21
     */
    public static MenuItemLocalDataSource getInstance(IMenuItemDao menuItemDao) {
        if (null == instance) {
            instance = new MenuItemLocalDataSource(menuItemDao);
        }
        return instance;
    }

    @Override
    public void getAllMenuItems(IOnLoadedCallback<List<MenuItem>> callback) {
        new LoadingAsyncTask<Unit, List<MenuItem>>(callback, param -> menuItemDao.getAllItems()).execute(new Unit());
    }

    @Override
    public void getItemById(String id, IOnLoadedCallback<MenuItem> callback) {
        new LoadingAsyncTask<String, MenuItem>(callback, param -> menuItemDao.getItemById(id)).execute(id);
    }

    @Override
    public void getItemsByGroupId(String groupId, IOnLoadedCallback<List<MenuItem>> callback) {
        new LoadingAsyncTask<String, List<MenuItem>>(callback, param -> menuItemDao.getItemsByGroupId(groupId)).execute(groupId);
    }

    @Override
    public void addNewMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback) {
        new LoadingAsyncTask<MenuItem, Boolean>(callback, param -> menuItemDao.addNewMenuItem(item)).execute(item);
    }

    @Override
    public void updateMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback) {
        new LoadingAsyncTask<MenuItem, Boolean>(callback, param -> menuItemDao.updateMenuItem(item)).execute(item);
    }

    @Override
    public void deleteMenuItem(MenuItem item, IOnLoadedCallback<Boolean> callback) {
        new LoadingAsyncTask<MenuItem, Boolean>(callback, param -> menuItemDao.deleteItem(item)).execute(item);
    }
}
