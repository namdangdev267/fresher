package vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.dto.GroupIcon;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class GroupIconAdapter extends BaseRecyclerViewAdapter<GroupIcon, GroupIconViewHolder> {
    @NonNull
    @Override
    public GroupIconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_group_icon, parent, false);
        return new GroupIconViewHolder(itemView, listener);
    }

    /**
     * - Mục đích method: Cập nhật nhóm được người dùng chọn
     *
     * @param groupIcon Icon của nhóm được chọn
     *
     * @created_by KhanhNQ on 31-Jan-21
     */
    public void selectIcon(GroupIcon groupIcon) {
        for (GroupIcon icon : items) {
            icon.setSelected(icon.getName().equals(groupIcon.getName()));
        }
        notifyDataSetChanged();
    }
}
