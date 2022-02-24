package vn.com.misa.cukcukstarterclone.ui.menu.itemlist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuItemRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.AddNewMenuItemEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.ChangeMenuItemGroupEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.ModifyMenuItemEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemlist.MenuItemAdapter;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 31-Jan-21
 */
public class ItemListFragment extends BaseFragment<ItemListContract.View, ItemListPresenter>
        implements ItemListContract.View {

    public static final String TAG = "ItemListFragment";

    private static final String ARG_GROUP = "argGroup";

    private MenuGroup mGroup;

    private final MenuItemAdapter menuItemAdapter = new MenuItemAdapter();

    private ItemListPresenter mPresenter;

    private IItemListFragmentCallBack callback;

    public ItemListFragment() {
    }

    public static ItemListFragment newInstance(MenuGroup group, IItemListFragmentCallBack callback) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_GROUP, group);
        fragment.setArguments(args);
        fragment.callback = callback;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGroup = getArguments().getParcelable(ARG_GROUP);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_item_list;
    }

    @Override
    protected void bindViews(View view) {
        try {
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> {
                requireActivity().getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            });

            TextView tvGroupName = view.findViewById(R.id.tvGroupName);
            tvGroupName.setText(mGroup.getTitle());

            RecyclerView rcvMenuItem = view.findViewById(R.id.rcvMenuItem);
            menuItemAdapter.setItemClickListener((item, position) -> {
                modifyItem(item);
            });
            menuItemAdapter.setItemDeleteListener((item, position) -> {
                deleteItem(item);
            });
            rcvMenuItem.setAdapter(menuItemAdapter);

            FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);
            fabAdd.setOnClickListener(v -> addNewItem());
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
        EventBus.getDefault().register(this);
    }

    private void initPresenter() {
        try {
            IMenuItemRepository menuItemRepository = Injector.getMenuItemRepository(requireContext());

            mPresenter = new ItemListPresenter(menuItemRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật thông tin món ăn
     * - Sử dụng khi: Lắng nghe sự kiện thay đổi thông tin món ăn
     *
     * @param event Sự kiện MenuItem thay đổi thông tin
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateItem(ModifyMenuItemEvent event) {
        try {
            MenuItem item = event.item;
            if (mGroup.getId().equals(item.getGroupId())) {
                mPresenter.updateItem(item);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Thay đổi vị trí của món ăn trên view sang nhóm khác
     * - Sử dụng khi: Lắng nghe sự kiện món ăn được đổi sang một nhóm khác
     *
     * @param event Sự kiện MenuItem thay đổi Group
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMenuItemChangeGroup(ChangeMenuItemGroupEvent event) {
        try {
            String originalGroupId = event.originalGroupId;
            MenuItem item = event.item;
            if (mGroup.getId().equals(originalGroupId)) {
                mPresenter.updateItem(item);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Thêm mới một MenuItem
     * - Sử dụng khi: Lắng nghe sự kiện một món ăn được thêm mới
     *
     * @param event Sự kiện thêm mới một MenuItem
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMenuItemAdded(AddNewMenuItemEvent event) {
        try {
            MenuItem item = event.item;
            if (mGroup.getId().equals(item.getGroupId())) {
                mPresenter.addNewItem(item);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void modifyItem(MenuItem item) {
        callback.modifyMenuItem(item);
    }

    private void deleteItem(MenuItem item) {
        mPresenter.deleteItem(item);
    }

    private void addNewItem() {
        callback.addMenuItem();
    }

    @Override
    protected void initData() {
        mPresenter.getAllItem(mGroup.getId());
    }

    @Override
    public void showAllItems(List<MenuItem> menuItems) {
        menuItemAdapter.loadItems(menuItems);
    }

    @Override
    public void showAddedItem(MenuItem menuItem) {
        menuItemAdapter.insertItem(menuItem);
    }

    @Override
    public void removeItem(MenuItem menuItem) {
        menuItemAdapter.removeItem(menuItem);
    }

    @Override
    public void updateItem(MenuItem menuItem) {
        if (mGroup.getId().equals(menuItem.getGroupId())) {
            menuItemAdapter.updateItem(menuItem);
        } else menuItemAdapter.removeItem(menuItem);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter.detach();
        EventBus.getDefault().unregister(this);
    }

    public interface IItemListFragmentCallBack {
        void addMenuItem();

        void modifyMenuItem(MenuItem menuItem);
    }
}
