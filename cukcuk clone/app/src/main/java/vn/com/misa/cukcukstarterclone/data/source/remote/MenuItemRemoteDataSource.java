package vn.com.misa.cukcukstarterclone.data.source.remote;

import vn.com.misa.cukcukstarterclone.data.source.IMenuItemDataSource;

/**
 * @created_by KhanhNQ on 12-Jan-21.
 */
public class MenuItemRemoteDataSource implements IMenuItemDataSource.Remote {
    private static MenuItemRemoteDataSource instance;

    public static MenuItemRemoteDataSource getInstance() {
        if (null == instance) {
            instance = new MenuItemRemoteDataSource();
        }
        return instance;
    }
}
