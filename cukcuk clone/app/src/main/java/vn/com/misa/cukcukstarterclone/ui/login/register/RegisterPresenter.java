package vn.com.misa.cukcukstarterclone.ui.login.register;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.repository.IUserRepository;

/**
 * @created_by KhanhNQ on 08-Feb-2021.
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View view;

    private final IUserRepository userRepository;

    public RegisterPresenter(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, String password) {
        userRepository.register(username, password, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) {
                    view.registerSuccess();
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    @Override
    public void attach(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }
}
