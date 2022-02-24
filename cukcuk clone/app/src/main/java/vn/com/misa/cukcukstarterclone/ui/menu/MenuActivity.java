package vn.com.misa.cukcukstarterclone.ui.menu;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseActivity;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuGroupRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.menu.adapter.MenuGroupAdapter;
import vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.GroupDetailsFragment;
import vn.com.misa.cukcukstarterclone.ui.menu.itemlist.ItemListFragment;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemdetails.MenuItemDetailsFragment;
import vn.com.misa.cukcukstarterclone.utils.Utils;

public class MenuActivity extends BaseActivity<MenuContract.View, MenuPresenter>
        implements MenuContract.View,
        ItemListFragment.IItemListFragmentCallBack,
        GroupDetailsFragment.IGroupDetailsFragmentCallback {

    private final MenuGroupAdapter groupAdapter = new MenuGroupAdapter();

    private ArrayList<MenuGroup> menuGroups;

    private MenuPresenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_menu;
    }

    @Override
    protected void bindViews() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> {
                finish();
            });

            RecyclerView rcvMenuGroup = findViewById(R.id.rcvMenuGroup);
            groupAdapter.setItemClickListener((item, position) -> showGroupDetails(item));
            groupAdapter.setUpListener(
                    (item, position) -> editGroup(item),
                    (item, position) -> deleteGroup(item));
            rcvMenuGroup.setAdapter(groupAdapter);

            TextView tvAddGroup = findViewById(R.id.tvAddGroup);
            tvAddGroup.setOnClickListener(view -> addNewGroup());
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
    }

    private void addNewGroup() {
        try {
            GroupDetailsFragment groupDetailsFragment = GroupDetailsFragment.newInstance(null, this);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.root, groupDetailsFragment, GroupDetailsFragment.TAG).addToBackStack(GroupDetailsFragment.TAG);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void showGroupDetails(MenuGroup item) {
        try {
            ItemListFragment itemListFragment = ItemListFragment.newInstance(item, this);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.root, itemListFragment, ItemListFragment.TAG).addToBackStack(ItemListFragment.TAG);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void editGroup(MenuGroup item) {
        try {
            GroupDetailsFragment groupDetailsFragment = GroupDetailsFragment.newInstance(item, this);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.root, groupDetailsFragment, GroupDetailsFragment.TAG).addToBackStack(GroupDetailsFragment.TAG);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void deleteGroup(MenuGroup item) {
        try {
            mPresenter.deleteGroup(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void initPresenter() {
        try {
            IMenuGroupRepository menuGroupRepository = Injector.getMenuGroupRepository(this);

            mPresenter = new MenuPresenter(menuGroupRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void addMenuItem() {
        Fragment fragmentModifyItem = MenuItemDetailsFragment.newInstance(null, menuGroups);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.root, fragmentModifyItem, MenuItemDetailsFragment.TAG).addToBackStack(MenuItemDetailsFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        Fragment fragmentModifyItem = MenuItemDetailsFragment.newInstance(menuItem, menuGroups);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.root, fragmentModifyItem, MenuItemDetailsFragment.TAG).addToBackStack(MenuItemDetailsFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    protected void initData() {
        mPresenter.getAllGroups();
    }

    @Override
    public void showGroups(List<MenuGroup> menuGroups) {
        try {
            this.menuGroups = new ArrayList<>(menuGroups);
            groupAdapter.loadItems(menuGroups);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void onDeleteGroupSuccess(MenuGroup menuGroup) {
        try {
            groupAdapter.removeItem(menuGroup);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void onUpdateGroupDetails(MenuGroup menuGroup) {
        try {
            groupAdapter.updateItem(menuGroup);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void onAddGroupDetails(MenuGroup menuGroup) {
        try {
            groupAdapter.insertItem(menuGroup);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detach();
    }
}
