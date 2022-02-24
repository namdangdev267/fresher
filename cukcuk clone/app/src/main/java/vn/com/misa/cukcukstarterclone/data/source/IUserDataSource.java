package vn.com.misa.cukcukstarterclone.data.source;

import android.app.Activity;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;

/**
 * @created_by KhanhNQ on 08-Feb-2021.
 */
public interface IUserDataSource {
    interface Local {

    }

    interface Remote {
        void login(String username, String password, IOnLoadedCallback<Boolean> callback);

        void register( String username, String password, IOnLoadedCallback<Boolean> callback);
    }
}
