package vn.com.misa.cukcukstarterclone.ui.setup.step2;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * @created_by KhanhNQ on 12-Jan-21.
 */
public class SetUpContract {
    interface View extends BaseContract.View {
        void updateData(List<MenuGroup> menuGroups, List<MenuItem> menuItems);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void initDataForCoffeeShop();
        void initDataForBubbleTeaShop();
        void initDataForNoodleShop();
    }
}
