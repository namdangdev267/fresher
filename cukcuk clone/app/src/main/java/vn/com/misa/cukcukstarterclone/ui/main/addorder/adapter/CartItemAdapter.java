package vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.CartItemDto;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public class CartItemAdapter extends BaseRecyclerViewAdapter<CartItemDto, CartItemViewHolder> {

    private OnItemClickListener<CartItemDto> decreaseQuantityListener;
    private OnItemClickListener<CartItemDto> increaseQuantityListener;

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_item, parent, false);
        return new CartItemViewHolder(itemView, listener, decreaseQuantityListener, increaseQuantityListener);
    }

    public void setCartItemActionListener(OnItemClickListener<CartItemDto> decreaseQuantityListener,
                                          OnItemClickListener<CartItemDto> increaseQuantityListener) {
        this.decreaseQuantityListener = decreaseQuantityListener;
        this.increaseQuantityListener = increaseQuantityListener;
    }

    /**
     * - Mục đích method: Cập nhật danh dách món ăn trong adapter
     *
     * @param menuItems danh sách món ăn cần cập nhật
     *
     * @created_by KhanhNQ on 28-Jan-21
     */
    public void updateCartItemInfo(List<MenuItemDto> menuItems) {
        try {
            for (int i = 0; i < items.size(); i++) {
                for (int j = 0; j < menuItems.size(); j++) {
                    if (items.get(i).getItemId().equals(menuItems.get(j).getId())) {
                        items.get(i).setName(menuItems.get(j).getName());
                        items.get(i).setPrice(menuItems.get(j).getPrice());
                        notifyItemChanged(i);
                    }
                }
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Thêm mới một món ăn vào danh sách
     *
     * @created_by KhanhNQ on 28-Jan-21
     */
    public void insertItem(CartItem newItem, List<MenuItemDto> menuItems) {
        try {
            CartItemDto cartItemDto = new CartItemDto(newItem);
            for (MenuItemDto item : menuItems) {
                if (item.getId().equals(newItem.getProductId())) {
                    cartItemDto.setName(item.getName());
                    cartItemDto.setPrice(item.getPrice());
                    break;
                }
            }
            items.add(cartItemDto);
            notifyItemInserted(items.indexOf(cartItemDto));
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: cập nhật CartItem
     *
     * @created_by KhanhNQ on 28-Jan-21
     */
    public void updateItem(CartItemDto cartItemDto) {
        for (CartItemDto item : items) {
            if (item.getId().equals(cartItemDto.getId())) {
                int index = items.indexOf(item);
                item.setQuantity(cartItemDto.getQuantity());
                notifyItemChanged(index);
                break;
            }
        }
    }
}
