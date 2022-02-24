package vn.com.misa.cukcukstarterclone.ui.order.orderdetails.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.ui.order.dto.CartItemDto;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class CartItemAdapter extends BaseRecyclerViewAdapter<CartItemDto, CartItemViewHolder> {
    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_item_in_order, parent, false);
        return new CartItemViewHolder(itemView, listener);
    }
}
