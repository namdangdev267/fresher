package vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.data.model.MenuItem;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuGroupDto;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;

/**
 * @created_by KhanhNQ on 26-Jan-21.
 */
public class MenuGroupAdapter extends BaseRecyclerViewAdapter<MenuGroupDto, MenuGroupViewHolder> {
    @NonNull
    @Override
    public MenuGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_group, parent, false);
        return new MenuGroupViewHolder(itemView, listener);
    }

    /**
     * - Mục đích method: Cập nhật lại adapter khi chọn nhóm khác
     *
     * @param previousGroupId nhóm món ăn đang được chọn từ trước
     * @param selectedGroupId nhóm món ăn được người dùng chọn
     * @created_by KhanhNQ on 27-Jan-21
     */
    public void updateSelectedGroup(String previousGroupId, String selectedGroupId) {
        int indexPrevious = -1;
        int indexCurrent = -1;

        for (int i = 0; i < items.size(); i++) {
            if (previousGroupId.equals(items.get(i).getId())) indexPrevious = i;
            if (selectedGroupId.equals(items.get(i).getId())) indexCurrent = i;
        }
        items.get(indexPrevious).setSelected(false);
        notifyItemChanged(indexPrevious);
        items.get(indexCurrent).setSelected(true);
        notifyItemChanged(indexCurrent);
    }

    /**
     * - Mục đích method: Update số lượng món ăn đã được chọn trong từng nhóm
     *
     * @param cartItems danh sách món ăn đã được chọn
     * @param menuItems danh sách món ăn trong CSDL
     * @created_by KhanhNQ on 27-Jan-21
     */
    public void updateCartItems(List<CartItem> cartItems, List<MenuItemDto> menuItems) {
        for (int i = 0; i < items.size(); i++) {
            int totalQuantity = 0;

            for (int j = 0; j < cartItems.size(); j++) {
                for (int k = 0; k < menuItems.size(); k++) {
                    if (cartItems.get(j).getProductId().equals(menuItems.get(k).getId())
                            && (menuItems.get(k).getGroupId().equals(items.get(i).getId()))) {
                        totalQuantity += cartItems.get(j).getQuantity();
                    }
                }
            }
            items.get(i).setItemCount(totalQuantity);
            notifyItemChanged(i);
        }
    }
}
