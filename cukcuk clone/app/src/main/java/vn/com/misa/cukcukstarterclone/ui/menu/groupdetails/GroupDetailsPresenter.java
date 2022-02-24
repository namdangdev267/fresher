package vn.com.misa.cukcukstarterclone.ui.menu.groupdetails;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;
import vn.com.misa.cukcukstarterclone.data.repository.IMenuGroupRepository;
import vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.dto.GroupIcon;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class GroupDetailsPresenter implements GroupDetailsContract.Presenter {

    private GroupDetailsContract.View view;

    private final IMenuGroupRepository menuGroupRepository;

    public GroupDetailsPresenter(IMenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Override
    public void getGroupIcon(String selectedGroup) {
        String[] icons = ((GroupDetailsFragment) view).getContext().getResources().getStringArray(R.array.group_icon);
        List<GroupIcon> groupIcons = new ArrayList<>();
        for (String icon : icons) {
            groupIcons.add(
                    selectedGroup.equals(icon) ? new GroupIcon(icon, true) : new GroupIcon(icon, false)
            );
        }
        view.showListGroupMenu(groupIcons);
    }

    @Override
    public void updateGroup(MenuGroup menuGroup) {
        menuGroupRepository.updateMenuGroup(menuGroup, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) view.updateGroup(menuGroup);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    @Override
    public void addNewGroup(MenuGroup menuGroup) {
        menuGroupRepository.addNewMenuGroup(menuGroup, new IOnLoadedCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isSuccess) {
                if (isSuccess) view.addGroup(menuGroup);
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    @Override
    public void attach(GroupDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
