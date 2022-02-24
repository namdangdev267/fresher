package vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemlist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.AddNewMenuItemEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.ChangeMenuItemGroupEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.DeleteMenuItemEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.ModifyMenuItemEvent;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 14-Jan-21
 */
public class MenuItemListSettingFragment extends BaseFragment<MenuItemListSettingContract.View, MenuItemListSettingPresenter> implements MenuItemListSettingContract.View {

    private static final String ARG_GROUP_ID = "group_id";
    private static final String ARG_ITEMS = "items";
    private static final String ARG_GROUPS = "groups";

    private IOnModifyMenuItem callback;

    private String mCurrentGroupId;
    private List<MenuItem> mItems = new ArrayList<>();
    private ArrayList<MenuGroup> mGroups = new ArrayList<>();

    private final MenuItemAdapter mMenuItemAdapter = new MenuItemAdapter();

    private MenuItemListSettingContract.Presenter mPresenter;

    public MenuItemListSettingFragment() {
    }

    public static MenuItemListSettingFragment newInstance(
            String groupId,
            ArrayList<MenuItem> items,
            List<MenuGroup> groups,
            IOnModifyMenuItem callback) {
        MenuItemListSettingFragment fragment = new MenuItemListSettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_GROUP_ID, groupId);
        args.putParcelableArrayList(ARG_ITEMS, items);
        args.putParcelableArrayList(ARG_GROUPS, new ArrayList<>(groups));
        fragment.setArguments(args);
        fragment.callback = callback;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mCurrentGroupId = getArguments().getString(ARG_GROUP_ID);
            this.mItems = getArguments().getParcelableArrayList(ARG_ITEMS);
            this.mGroups = getArguments().getParcelableArrayList(ARG_GROUPS);
        }

        EventBus.getDefault().register(this);
    }

    /**
     * - Mục đích method: detach view khỏi presenter để tránh strong preference
     *
     * @created_by KhanhNQ on 14-Jan-21
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);

        mPresenter.detach();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_menu_item_list;
    }

    /**
     * - Mục đích method: ánh xạ view, khởi tạo các biến
     *
     * @param view View
     * @created_by KhanhNQ on 14-Jan-21
     */
    @Override
    protected void bindViews(View view) {
        try {
            mPresenter = new MenuItemListSettingPresenter();
            mPresenter.attach(this);

            RecyclerView rcvMenuItem = view.findViewById(R.id.rcvMenuItem);
            mMenuItemAdapter.setItemClickListener((item, position) -> {
                modifyItem(item);
            });
            mMenuItemAdapter.setItemDeleteListener((item, position) -> {
                deleteItem(item);
            });
            rcvMenuItem.setAdapter(mMenuItemAdapter);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật thông tin món ăn
     * - Sử dụng khi: Lắng nghe sự kiện thay đổi thông tin món ăn
     *
     * @param event Sự kiện MenuItem thay đổi thông tin
     * @created_by KhanhNQ on 21-Jan-21
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateItem(ModifyMenuItemEvent event) {
        MenuItem item = event.item;
        if (mCurrentGroupId.equals(item.getGroupId())) {
            updateItem(item);
        }
    }

    /**
     * - Mục đích method: Thay đổi vị trí của món ăn trên view sang nhóm khác
     * - Sử dụng khi: Lắng nghe sự kiện món ăn được đổi sang một nhóm khác
     *
     * @param event Sự kiện MenuItem thay đổi Group
     * @created_by KhanhNQ on 21-Jan-21
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMenuItemChangeGroup(ChangeMenuItemGroupEvent event) {
        String originalGroupId = event.originalGroupId;
        MenuItem item = event.item;
        if (mCurrentGroupId.equals(originalGroupId)) {
            deleteItem(item);
            Log.e("TAG", "onMenuItemChangeGroup: DELETE" + mItems.size());
        }
        if (mCurrentGroupId.equals(item.getGroupId())) {
            addItem(item);
            Log.e("TAG", "onMenuItemChangeGroup: ADD" + mItems.size());
        }
    }

    /**
     * - Mục đích method: Thêm mới một MenuItem
     * - Sử dụng khi: Lắng nghe sự kiện một món ăn được thêm mới
     *
     * @param event Sự kiện thêm mới một MenuItem
     * @created_by KhanhNQ on 22-Jan-21
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMenuItemAdded(AddNewMenuItemEvent event) {
        MenuItem item = event.item;
        if (mCurrentGroupId.equals(item.getGroupId())) {
            addItem(item);
            Log.e("TAG", "onMenuItemChangeGroup: ADD" + mItems.size());
        }
    }

    /**
     * - Mục đích method: Cập nhật dữ liệu lên view
     *
     * @created_by KhanhNQ on 14-Jan-21
     */
    @Override
    protected void initData() {
        try {
            mMenuItemAdapter.loadItems(mItems);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Mở màn hình chỉnh sửa MenuItem
     * - Sử dụng khi: Bấm vào item bất kì trên RecyclerView
     *
     * @param item Item cần chỉnh sửa
     * @created_by KhanhNQ on 20-Jan-21
     */
    private void modifyItem(MenuItem item) {
        callback.showItemDetails(item, mGroups);
    }

    /**
     * - Mục đích method: Thêm mới một item vào danh sách
     * - Sử dụng khi: Người dùng thêm mới một item hoặc item được chuyển từ group khác sang
     *
     * @param item Thông tin của item được thêm mới vào
     * @created_by KhanhNQ on 20-Jan-21
     */
    private void addItem(MenuItem item) {
        try {
            mItems.add(item);
            mMenuItemAdapter.insertItem(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật thông tin item đã được chỉnh sửa lên View
     *
     * @param item Thông tin item đã được chỉnh sửa
     * @created_by KhanhNQ on 20-Jan-21
     */
    private void updateItem(MenuItem item) {
        try {
            int modifiedItemIndex = -1; // Giá trị khởi tạo
            for (MenuItem element : mItems) {
                if (item.getId().equals(element.getId())) {
                    modifiedItemIndex = mItems.indexOf(element);
                    break;
                }
            }
            if (-1 != modifiedItemIndex) { // Nếu giá trị khởi tạo đã được thay đổi
                mMenuItemAdapter.updateItem(modifiedItemIndex, item);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xoá Item khỏi RecyclerView
     * - Sử dụng khi: Người dùng xoá item khỏi Menu hoặc di chuyển item sang Group khác
     *
     * @param item Thông tin item cần xoá khỏi danh sách
     * @created_by KhanhNQ on 20-Jan-21
     */
    private void deleteItem(MenuItem item) {
        try {
            for (MenuItem element : mItems) {
                if (item.getId().equals(element.getId())) {
                    mItems.remove(element);
                    mMenuItemAdapter.removeItem(element);
                    EventBus.getDefault().post(new DeleteMenuItemEvent(element));

                    break;
                }
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    public interface IOnModifyMenuItem {
        void showItemDetails(MenuItem menuItem, ArrayList<MenuGroup> menuGroups);
    }
}
