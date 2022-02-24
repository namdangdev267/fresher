package vn.com.misa.cukcukstarterclone.ui.menu.groupdetails;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.dto.GroupIcon;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class GroupDetailsContract {
    interface View extends BaseContract.View {
        void showListGroupMenu(List<GroupIcon> groupIcons);

        void updateGroup(MenuGroup menuGroup);

        void addGroup(MenuGroup menuGroup);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getGroupIcon(String selectedGroup);

        void updateGroup(MenuGroup menuGroup);

        void addNewGroup(MenuGroup menuGroup);
    }
}
