package vn.com.misa.cukcukstarterclone.ui.menu.itemlist;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * - Mục đích Class:
 *
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class ItemListContract {
    interface View extends BaseContract.View {
        void showAllItems(List<MenuItem> menuItems);

        void showAddedItem(MenuItem menuItem);

        void removeItem(MenuItem menuItem);

        void updateItem(MenuItem menuItem);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getAllItem(String groupId);

        void updateItem(MenuItem menuItem);

        void addNewItem(MenuItem menuItem);

        void deleteItem(MenuItem menuItem);
    }
}
