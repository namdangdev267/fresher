package vn.com.misa.cukcukstarterclone.ui.menu.groupdetails;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuGroupRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.adapter.GroupIconAdapter;
import vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.dto.GroupIcon;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class GroupDetailsFragment extends BaseFragment<GroupDetailsContract.View, GroupDetailsPresenter>
        implements GroupDetailsContract.View {

    public static final String TAG = "GroupDetailsFragment";

    private static final String ARG_GROUP = "argGroup";

    private static final int FLAG_CREATE_NEW_GROUP = 0;
    private static final int FLAG_UPDATE_GROUP = 1;
    private int flag = -1;

    private MenuGroup mGroup;
    private String mGroupIcon;

    private TextInputEditText edtGroupName;

    private final GroupIconAdapter groupIconAdapter = new GroupIconAdapter();

    private GroupDetailsPresenter mPresenter;

    private IGroupDetailsFragmentCallback callback;

    public static GroupDetailsFragment newInstance(MenuGroup group, IGroupDetailsFragmentCallback callback) {
        GroupDetailsFragment fragment = new GroupDetailsFragment();
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
        return R.layout.fragment_group_details;
    }

    @Override
    protected void bindViews(View view) {
        try {
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> popFragment());

            edtGroupName = view.findViewById(R.id.edtGroupName);
            Button btnSave = view.findViewById(R.id.btnSave);

            RecyclerView rcvGroupIcon = view.findViewById(R.id.rcvGroupIcon);
            groupIconAdapter.setItemClickListener((item, position) -> selectGroup(item));
            rcvGroupIcon.setAdapter(groupIconAdapter);

            btnSave.setOnClickListener(v -> saveGroup());
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
    }

    private void selectGroup(GroupIcon item) {
        try {
            mGroupIcon = item.getName();
            item.setSelected(true);
            groupIconAdapter.selectIcon(item);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void saveGroup() {
        try {
            if (Objects.requireNonNull(edtGroupName.getText()).toString().isEmpty()) {
                edtGroupName.setError(requireContext().getString(R.string.error_menu_group_empty));
            } else {
                mGroup.setTitle(edtGroupName.getText().toString());
                mGroup.setIconUrl(mGroupIcon);

                switch (flag) {
                    case FLAG_CREATE_NEW_GROUP:
                        mPresenter.addNewGroup(mGroup);
                        break;
                    case FLAG_UPDATE_GROUP:
                        mPresenter.updateGroup(mGroup);
                        break;
                }
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void initPresenter() {
        try {
            IMenuGroupRepository menuGroupRepository = Injector.getMenuGroupRepository(requireContext());

            mPresenter = new GroupDetailsPresenter(menuGroupRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    protected void initData() {
        try {
            if (mGroup == null) {
                mGroup = new MenuGroup("", "do_uong");
                flag = FLAG_CREATE_NEW_GROUP;
            } else {
                flag = FLAG_UPDATE_GROUP;
            }
            mGroupIcon = mGroup.getIconUrl();

            edtGroupName.setText(mGroup.getTitle());
            mPresenter.getGroupIcon(mGroupIcon);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    @Override
    public void showListGroupMenu(List<GroupIcon> groupIcons) {
        groupIconAdapter.loadItems(groupIcons);
    }

    @Override
    public void updateGroup(MenuGroup menuGroup) {
        callback.onUpdateGroupDetails(menuGroup);
        popFragment();
    }

    @Override
    public void addGroup(MenuGroup menuGroup) {
        callback.onAddGroupDetails(menuGroup);
        popFragment();
    }

    private void popFragment() {
        requireActivity().getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter.detach();
    }

    public interface IGroupDetailsFragmentCallback {
        void onUpdateGroupDetails(MenuGroup menuGroup);

        void onAddGroupDetails(MenuGroup menuGroup);
    }
}
