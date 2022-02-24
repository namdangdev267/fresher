package vn.com.misa.cukcukstarterclone.ui.login.register;

import vn.com.misa.cukcukstarterclone.base.BaseContract;

/**
 * @created_by KhanhNQ on 08-Feb-2021.
 */
public class RegisterContract {
    interface View extends BaseContract.View {
        void registerSuccess();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void register(String username, String password);
    }
}
