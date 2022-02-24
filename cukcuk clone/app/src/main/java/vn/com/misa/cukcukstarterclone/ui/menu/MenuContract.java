package vn.com.misa.cukcukstarterclone.ui.menu;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;

/**
 * - Mục đích Class:
 *
 * @created_by KhanhNQ on 30-Jan-2021.
 */
public class MenuContract {
    interface View extends BaseContract.View {
        void showGroups(List<MenuGroup> menuGroups);

        void onDeleteGroupSuccess(MenuGroup menuGroup);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getAllGroups();

        void deleteGroup(MenuGroup menuGroup);
    }
}
