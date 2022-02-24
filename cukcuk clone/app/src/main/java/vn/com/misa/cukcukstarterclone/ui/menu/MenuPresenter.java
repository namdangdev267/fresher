package vn.com.misa.cukcukstarterclone.ui.menu;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuGroupRepository;

/**
 * - Mục đích Class:
 *
 * @created_by KhanhNQ on 30-Jan-2021.
 */
public class MenuPresenter implements MenuContract.Presenter {

    private MenuContract.View view;

    private final IMenuGroupRepository menuGroupRepository;

    public MenuPresenter(IMenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Override
    public void attach(MenuContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void getAllGroups() {
        menuGroupRepository.getAllMenuGroups(new IOnLoadedCallback<List<MenuGroup>>() {
            @Override
            public void onSuccess(List<MenuGroup> data) {
                view.showGroups(data);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void deleteGroup(MenuGroup menuGroup) {
        menuGroupRepository.deleteMenuGroup(menuGroup, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) view.onDeleteGroupSuccess(menuGroup);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }
}
