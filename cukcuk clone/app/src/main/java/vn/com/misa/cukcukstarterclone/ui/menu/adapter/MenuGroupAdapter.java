package vn.com.misa.cukcukstarterclone.ui.menu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;

/**
 * @created_by KhanhNQ on 30-Jan-2021.
 */
public class MenuGroupAdapter extends BaseRecyclerViewAdapter<MenuGroup, MenuGroupViewHolder> {

    private OnItemClickListener<MenuGroup> editListener;
    private OnItemClickListener<MenuGroup> deleteListener;

    public void setUpListener(OnItemClickListener<MenuGroup> editListener,
                              OnItemClickListener<MenuGroup> deleteListener) {
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public MenuGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new MenuGroupViewHolder(itemView, listener, editListener, deleteListener);
    }

    /**
     * - Mục đích method: Cập nhật nhóm món ăn
     *
     * @param menuGroup
     *
     * @created_by KhanhNQ on 30-Jan-21
     */
    public void updateItem(MenuGroup menuGroup) {
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (menuGroup.getId().equals(items.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            items.set(index, menuGroup);
            notifyItemChanged(index);
        }
    }
}
