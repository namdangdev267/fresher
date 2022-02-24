package vn.com.misa.cukcukstarterclone.ui.main.listorders.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.Cart;

/**
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class CartAdapter extends BaseRecyclerViewAdapter<Cart, CartViewHolder> {

    private OnItemClickListener<Cart> cancelListener;
    private OnItemClickListener<Cart> checkoutListener;

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(itemView, listener, cancelListener, checkoutListener);
    }

    public void setCartActionListener(OnItemClickListener<Cart> cancelListener,
                                      OnItemClickListener<Cart> checkoutListener) {
        this.cancelListener = cancelListener;
        this.checkoutListener = checkoutListener;
    }
}
