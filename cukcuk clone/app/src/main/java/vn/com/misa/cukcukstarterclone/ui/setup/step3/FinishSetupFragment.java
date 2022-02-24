package vn.com.misa.cukcukstarterclone.ui.setup.step3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.data.repository.MenuGroupRepository;
import vn.com.misa.cukcukstarterclone.data.repository.MenuItemRepository;
import vn.com.misa.cukcukstarterclone.data.source.local.MenuGroupLocalDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.MenuItemLocalDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.MenuGroupDao;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.MenuItemDao;
import vn.com.misa.cukcukstarterclone.data.source.local.database.AppDatabase;
import vn.com.misa.cukcukstarterclone.data.source.remote.MenuGroupRemoteDataSource;
import vn.com.misa.cukcukstarterclone.data.source.remote.MenuItemRemoteDataSource;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 14-Jan-21
 */
public class FinishSetupFragment extends BaseFragment<FinishSetupContract.View, FinishSetupPresenter>
        implements FinishSetupContract.View {

    public static final String TAG = "FinishSetupFragment";
    private static final String ARG_MENU_GROUPS = "groups";
    private static final String ARG_MENU_ITEMS = "items";

    private List<MenuGroup> mGroups;
    private List<MenuItem> mItems;

    private boolean isSavedGroups = false;
    private boolean isSavedItems = false;

    private IFinishSetUpCallback callback;

    private FinishSetupPresenter mPresenter;

    public FinishSetupFragment() {
    }

    /**
     * - Mục đích method: Khởi tạo 1 instance mới của class từ 2 đối số
     *
     * @param groups danh sách MenuGroup đã được chọn
     * @param items  danh sách MenuItem đã được chọn
     * @created_by KhanhNQ on 14-Jan-21
     */
    public static FinishSetupFragment newInstance(List<MenuGroup> groups,
                                                  List<MenuItem> items,
                                                  IFinishSetUpCallback callback) {
        FinishSetupFragment fragment = new FinishSetupFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_MENU_GROUPS, new ArrayList<>(groups));
        args.putParcelableArrayList(ARG_MENU_ITEMS, new ArrayList<>(items));
        fragment.setArguments(args);
        fragment.callback = callback;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mGroups = getArguments().getParcelableArrayList(ARG_MENU_GROUPS);
            mItems = getArguments().getParcelableArrayList(ARG_MENU_ITEMS);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_finish_setup;
    }

    @Override
    protected void bindViews(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> popFragment());

        Button btnStart = view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> saveDataAndContinue());

        initPresenter();
    }

    private void initPresenter() {
        try {
            MenuGroupRepository groupRepository = Injector.getMenuGroupRepository(requireContext());
            MenuItemRepository itemRepository = Injector.getMenuItemRepository(requireContext());

            mPresenter = new FinishSetupPresenter(itemRepository, groupRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    protected void initData() {
    }

    private void saveDataAndContinue() {
        try {
            mPresenter.saveMenuGroups(mGroups);
            mPresenter.saveMenuItems(mItems);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void popFragment() {
        try {
            requireActivity().getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void onSaveGroupsSuccess() {
        isSavedGroups = true;
        onSaveSuccess();
    }

    @Override
    public void onSaveItemsSuccess() {
        isSavedItems = true;
        onSaveSuccess();
    }

    private void onSaveSuccess() {
        if (isSavedGroups && isSavedItems) callback.finishSetup();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.detach();
    }

    public interface IFinishSetUpCallback {
        void finishSetup();
    }
}
