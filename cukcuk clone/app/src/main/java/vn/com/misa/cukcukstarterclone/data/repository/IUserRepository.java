package vn.com.misa.cukcukstarterclone.data.repository;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;

/**
 * @created_by KhanhNQ on 08-Feb-2021.
 */
public interface IUserRepository {
    void login(String username, String password, IOnLoadedCallback<Boolean> callback);

    void register(String username, String password, IOnLoadedCallback<Boolean> callback);
}
