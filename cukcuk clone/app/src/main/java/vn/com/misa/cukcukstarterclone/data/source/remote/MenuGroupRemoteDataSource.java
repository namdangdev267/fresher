package vn.com.misa.cukcukstarterclone.data.source.remote;

import vn.com.misa.cukcukstarterclone.data.source.IMenuGroupDataSource;

/**
 * @created_by KhanhNQ on 22-Jan-21.
 */
public class MenuGroupRemoteDataSource implements IMenuGroupDataSource.Remote {
    private static MenuGroupRemoteDataSource instance;

    public static MenuGroupRemoteDataSource getInstance() {
        if (null == instance) {
            instance = new MenuGroupRemoteDataSource();
        }
        return instance;
    }
}
