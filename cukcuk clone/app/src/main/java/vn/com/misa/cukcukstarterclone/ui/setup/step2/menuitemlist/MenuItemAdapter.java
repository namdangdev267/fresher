package vn.com.misa.cukcukstarterclone.ui.setup.step2.menuitemlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;

/**
 * @created_by KhanhNQ on 13-Jan-21.
 */
public class MenuItemAdapter extends BaseRecyclerViewAdapter<MenuItem, MenuItemViewHolder> {

    private OnItemClickListener<MenuItem> deleteListener;

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_item_setting, parent, false);
        return new MenuItemViewHolder(itemView, listener, deleteListener);
    }

    public void setItemDeleteListener(OnItemClickListener<MenuItem> deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void updateItem(MenuItem menuItem) {
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (menuItem.getId().equals(items.get(i).getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            items.set(index, menuItem);
            notifyItemChanged(index);
        }
    }
}
