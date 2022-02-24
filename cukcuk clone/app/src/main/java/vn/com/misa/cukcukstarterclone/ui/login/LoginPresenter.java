package vn.com.misa.cukcukstarterclone.ui.login;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.repository.IUserRepository;

/**
 * - Mục đích Class:
 *
 * @created_by KhanhNQ on 07-Feb-2021.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private final IUserRepository userRepository;

    public LoginPresenter(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void attach(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void login(String username, String password) {
        userRepository.login(username, password, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) {
                    view.loggedInSuccess();
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }
}
