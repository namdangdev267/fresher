package vn.com.misa.cukcukstarterclone.data.repository;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.source.IUserDataSource;

/**
 * @created_by KhanhNQ on 08-Feb-2021.
 */
public class UserRepository implements IUserRepository {

    private final IUserDataSource.Remote remoteDataSource;

    private UserRepository(IUserDataSource.Remote userRemoteDataSource) {
        this.remoteDataSource = userRemoteDataSource;
    }

    private static UserRepository instance;

    public static UserRepository getInstance(IUserDataSource.Remote userRemoteDataSource) {
        if (instance == null) {
            instance = new UserRepository(userRemoteDataSource);
        }
        return instance;
    }

    @Override
    public void login(String username, String password, IOnLoadedCallback<Boolean> callback) {
        remoteDataSource.login(username, password, callback);
    }

    @Override
    public void register(String username, String password, IOnLoadedCallback<Boolean> callback) {
        remoteDataSource.register(username, password, callback);
    }
}
