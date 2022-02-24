package vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;

/**
 * @created_by KhanhNQ on 26-Jan-21.
 */
public class MenuItemAdapter extends BaseRecyclerViewAdapter<MenuItemDto, MenuItemViewHolder> {
    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_item, parent, false);
        return new MenuItemViewHolder(itemView, listener);
    }

    /**
     * - Mục đích method: Update số lượng món này đã được chọn trong đơn hàng
     *
     * @param  cartItems danh sách món ăn trong giỏ hàng
     *
     * @created_by KhanhNQ on 27-Jan-21
     */
    public void updateCartItems(List<CartItem> cartItems) {
        for (int i = 0; i < items.size(); i++) {
            int totalQuantity = 0;
            for (int j = 0; j < cartItems.size(); j++) {
                if (cartItems.get(j).getProductId().equals(items.get(i).getId())) {
                    totalQuantity += cartItems.get(j).getQuantity();
                }
            }
            items.get(i).setQuantity(totalQuantity);
            notifyItemChanged(i);
        }
    }
}
