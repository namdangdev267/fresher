package vn.com.misa.cukcukstarterclone.ui.login;

import vn.com.misa.cukcukstarterclone.base.BaseContract;

/**
 * @created_by KhanhNQ on 07-Feb-2021.
 */
public class LoginContract {
    interface View extends BaseContract.View {
        void loggedInSuccess();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void login(String username, String password);
    }
}
