package vn.com.misa.cukcukstarterclone.ui.setup.step3;

import android.util.Log;

import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuGroupRepository;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuItemRepository;

/**
 * - Mục đích Class:
 * - Sử dụng khi:
 *
 * @created_by KhanhNQ on 22-Jan-21.
 */
public class FinishSetupPresenter implements FinishSetupContract.Presenter {

    private FinishSetupContract.View view;
    private final IMenuItemRepository menuItemRepository;
    private final IMenuGroupRepository menuGroupRepository;

    public FinishSetupPresenter(IMenuItemRepository menuItemRepository, IMenuGroupRepository menuGroupRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuGroupRepository = menuGroupRepository;
    }

    @Override
    public void saveMenuGroups(List<MenuGroup> menuGroups) {
        String lastItemId = menuGroups.get(menuGroups.size() - 1).getId();
        for (MenuGroup element : menuGroups) {
            menuGroupRepository.addNewMenuGroup(element, new IOnLoadedCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean isSuccess) {
                    if (isSuccess) {
                        Log.d("TAG", "onSuccess: " + element.toString());
                    } else {
                        view.showErrorMessage(R.string.save_data_failed);
                    }
                    if (element.getId().equals(lastItemId)) view.onSaveGroupsSuccess();
                }

                @Override
                public void onFailure(Exception e) {
                    view.showErrorMessage(e.getMessage());
                }
            });
        }
    }

    @Override
    public void saveMenuItems(List<MenuItem> menuItems) {
        String lastItemId = menuItems.get(menuItems.size() - 1).getId();
        for (MenuItem element : menuItems) {
            menuItemRepository.addNewMenuItem(element, new IOnLoadedCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean isSuccess) {
                    if (isSuccess) {
                        Log.d("TAG", "onSuccess: " + element.toString());
                    } else {
                        view.showErrorMessage(R.string.save_data_failed);
                    }
                    if (element.getId().equals(lastItemId)) view.onSaveItemsSuccess();
                }

                @Override
                public void onFailure(Exception e) {
                    view.showErrorMessage(e.getMessage());
                }
            });
        }
    }

    @Override
    public void attach(FinishSetupContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
