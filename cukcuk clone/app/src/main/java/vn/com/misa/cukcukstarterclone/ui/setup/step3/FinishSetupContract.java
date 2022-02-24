package vn.com.misa.cukcukstarterclone.ui.setup.step3;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * @created_by KhanhNQ on 22-Jan-21.
 */
public class FinishSetupContract {
    interface View extends BaseContract.View {
        void onSaveGroupsSuccess();

        void onSaveItemsSuccess();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void saveMenuGroups(List<MenuGroup> menuGroups);

        void saveMenuItems(List<MenuItem> menuItems);
    }
}
