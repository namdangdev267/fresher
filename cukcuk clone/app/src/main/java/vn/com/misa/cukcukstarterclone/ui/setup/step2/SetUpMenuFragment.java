package vn.com.misa.cukcukstarterclone.ui.setup.step2;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.model.BusinessType;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.event.DeleteMenuItemEvent;
import vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemlist.MenuItemListSettingFragment;
import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemdetails.MenuItemDetailsFragment.ARG_ADD_ITEM;
import static vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemdetails.MenuItemDetailsFragment.ARG_MODIFIED_ITEM;
import static vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemdetails.MenuItemDetailsFragment.KEY_ADD_ITEM;
import static vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemdetails.MenuItemDetailsFragment.KEY_MODIFY_ITEM;

/**
 * @created_by KhanhNQ on 13-Jan-21
 */
public class SetUpMenuFragment extends BaseFragment<SetUpContract.View, SetUpPresenter> implements SetUpContract.View {

    public static final String TAG = "SetUpMenuFragment";

    private ISetUpMenuCallback callback;
    private MenuItemListSettingFragment.IOnModifyMenuItem mModifyItemCallback;


    private static final String ARG_TYPE = "type";
    private BusinessType mSelectedType;

    private TabLayout tabLayout;
    private ViewPager2 vpMenuItems;

    private List<MenuGroup> menuGroups = new ArrayList<>();
    private List<MenuItem> menuItems = new ArrayList<>();

    private SetUpPresenter presenter;

    public static SetUpMenuFragment newInstance(BusinessType businessType,
                                                ISetUpMenuCallback callback,
                                                MenuItemListSettingFragment.IOnModifyMenuItem modifyItemCallback) {
        SetUpMenuFragment fragment = new SetUpMenuFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TYPE, businessType);
        fragment.setArguments(args);
        fragment.callback = callback;
        fragment.mModifyItemCallback = modifyItemCallback;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mSelectedType = getArguments().getParcelable(ARG_TYPE);
        }

        EventBus.getDefault().register(this);

        getParentFragmentManager().setFragmentResultListener(KEY_ADD_ITEM, this, (requestKey, result) -> {
            MenuItem item = result.getParcelable(ARG_ADD_ITEM);
            onAddItem(item);
        });

        getParentFragmentManager().setFragmentResultListener(KEY_MODIFY_ITEM, this, (requestKey, result) -> {
            MenuItem item = result.getParcelable(ARG_MODIFIED_ITEM);
            updateItem(item);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_set_up_menu;
    }

    /**
     * - Mục đích method: ánh xạ view
     *
     * @param view View
     * @created_by KhanhNQ on 13-Jan-21
     */
    @Override
    protected void bindViews(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        vpMenuItems = view.findViewById(R.id.vpMenuItems);
        tabLayout = view.findViewById(R.id.tab_layout);
        TextView btnContinue = view.findViewById(R.id.btnContinue);
        FloatingActionButton fabAdd = view.findViewById(R.id.fabAdd);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String drawableUrl = Objects.requireNonNull(tab.getTag()).toString();
                tab.setIcon(getDrawable(getString(R.string.prefix_drawable_name) + drawableUrl + getString(R.string.postfix_drawable_name)));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                String drawableUrl = Objects.requireNonNull(tab.getTag()).toString();
                tab.setIcon(getDrawable(getString(R.string.prefix_drawable_name) + drawableUrl));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        presenter = new SetUpPresenter();
        presenter.attach(this);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> popFragment());

        btnContinue.setOnClickListener(v -> continueToStep3());
        fabAdd.setOnClickListener(v -> addNewItem());
    }

    private void popFragment() {
        try {
            requireActivity().getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: khởi tạo dữ liệu cho view
     *
     * @created_by KhanhNQ on 13-Jan-21
     */
    @Override
    protected void initData() {
        switch (mSelectedType.getId()) {
            case 0:
                presenter.initDataForCoffeeShop();
                break;
            case 1:
                presenter.initDataForBubbleTeaShop();
                break;
            case 2:
                presenter.initDataForNoodleShop();
                break;
            default:
                break;
        }
    }

    /**
     * - Mục đích method: Cập nhật data lên view với data được truyển lên từ presenter
     *
     * @param menuGroups danh sách menu group
     * @param menuItems  danh sách menu item
     * @created_by KhanhNQ on 13-Jan-21
     */
    @Override
    public void updateData(List<MenuGroup> menuGroups, List<MenuItem> menuItems) {
        this.menuGroups = menuGroups;
        this.menuItems = menuItems;

        MenuPagerAdapter pagerAdapter = new MenuPagerAdapter(
                getChildFragmentManager(),
                getLifecycle(),
                menuGroups,
                menuItems,
                mModifyItemCallback);

        vpMenuItems.setAdapter(pagerAdapter);
        vpMenuItems.setOffscreenPageLimit(20); // Fix cứng số tab trên màn hình để tránh reload

        new TabLayoutMediator(tabLayout, vpMenuItems, (tab, position) -> {
            tab.setTag(menuGroups.get(position).getIconUrl());

            tab.setText(menuGroups.get(position).getTitle());
            tab.setIcon(getDrawable(getString(R.string.prefix_drawable_name) + menuGroups.get(position).getIconUrl()));
        }
        ).attach();
    }

    /**
     * - Mục đích method: lấy Drawable từ tên
     *
     * @param drawableName tên drawable
     * @created_by KhanhNQ on 18-Jan-21
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getDrawable(String drawableName) {
        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier(drawableName, "drawable", getContext().getPackageName());
        return resources.getDrawable(resourceId);
    }

    /**
     * - Mục đích method: Mở dialog thêm một item mới
     *
     * @created_by KhanhNQ on 13-Jan-21
     */
    private void addNewItem() {
        try {
            callback.addNewItem(new ArrayList<>(menuGroups));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Thêm mới một item vào danh sách
     * - Sử dụng khi: Khi người dùng thêm mới một item, trở về màn hình setup
     *
     * @param item Item đã được thêm mới
     * @created_by KhanhNQ on 22-Jan-21
     */
    private void onAddItem(MenuItem item) {
        try {
            menuItems.add(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Update một item sau khi được chỉnh sửa thành công
     * - Sử dụng khi: Khi người dùng chỉnh sửa một item, trở về màn hình setup
     *
     * @param item Item đã được chỉnh sửa thông tin
     * @created_by KhanhNQ on 20-Jan-21
     */
    private void updateItem(MenuItem item) {
        try {
            int modifiedItemIndex = -1; // Giá trị khởi tạo
            for (MenuItem element : menuItems) {
                if (item.getId().equals(element.getId())) {
                    modifiedItemIndex = menuItems.indexOf(element);
                    break;
                }
            }
            if (-1 != modifiedItemIndex) { // Nếu giá trị khởi tạo đã được thay đổi
                menuItems.set(modifiedItemIndex, item);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Xoá thông tin món ăn khỏi danh sách món
     * - Sử dụng khi: Lắng nghe sự kiện thay xoá món ăn
     *
     * @param event Sự kiện xoá MenuItem
     * @created_by KhanhNQ on 22-Jan-21
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteItem(DeleteMenuItemEvent event) {
        MenuItem item = event.item;
        menuItems.remove(item);
    }

    /**
     * - Mục đích method: chuyển sang bước tiếp theo
     *
     * @created_by KhanhNQ on 13-Jan-21
     */
    private void continueToStep3() {
        callback.modifiedMenu(menuGroups, menuItems);
    }

    /**
     * - Mục đích method: hủy liên kết giữa view và presenter
     *
     * @created_by KhanhNQ on 13-Jan-21
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
        EventBus.getDefault().unregister(this);
    }

    public interface ISetUpMenuCallback {
        void addNewItem(ArrayList<MenuGroup> menuGroups);

        void modifiedMenu(List<MenuGroup> menuGroups, List<MenuItem> menuItems);
    }
}
