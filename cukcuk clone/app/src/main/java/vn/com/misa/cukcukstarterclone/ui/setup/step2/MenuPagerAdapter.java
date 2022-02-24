package vn.com.misa.cukcukstarterclone.ui.setup.step2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemlist.MenuItemListSettingFragment;

/**
 * - Mục đích class: Adapter cho PagerAdapter ở {@link SetUpMenuFragment}
 *
 * @created_by KhanhNQ on 07-Jan-21
 */
public class MenuPagerAdapter extends FragmentStateAdapter {

    private final List<MenuGroup> menuGroups;
    private final List<MenuItem> menuItems;

    private MenuItemListSettingFragment.IOnModifyMenuItem callback;

    public MenuPagerAdapter(FragmentManager fragmentManager,
                            Lifecycle lifecycle,
                            List<MenuGroup> menuGroups,
                            List<MenuItem> menuItems,
                            MenuItemListSettingFragment.IOnModifyMenuItem callback) {
        super(fragmentManager, lifecycle);
        this.menuGroups = menuGroups;
        this.menuItems = menuItems;
        this.callback = callback;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String currentGroupId = menuGroups.get(position).getId();
        ArrayList<MenuItem> items = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (currentGroupId.equals(item.getGroupId())) {
                items.add(item);
            }
        }
        return MenuItemListSettingFragment.newInstance(currentGroupId, items, menuGroups, callback);
    }

    @Override
    public int getItemCount() {
        return menuGroups.size();
    }
}
