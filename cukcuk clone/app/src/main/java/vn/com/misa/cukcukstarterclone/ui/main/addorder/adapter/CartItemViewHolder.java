package vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.CartItemDto;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 28-Jan-2021.
 */
public class CartItemViewHolder extends BaseViewHolder<CartItemDto> {

    private final TextView tvItemName;
    private final TextView tvItemPrice;
    private final ImageView ivMinus;
    private final ImageView ivPlus;
    private final TextView tvCartItemQuantity;

    OnItemClickListener<CartItemDto> decreaseQuantityListener;
    OnItemClickListener<CartItemDto> increaseQuantityListener;

    public CartItemViewHolder(@NonNull View itemView,
                              OnItemClickListener<CartItemDto> listener,
                              OnItemClickListener<CartItemDto> decreaseQuantityListener,
                              OnItemClickListener<CartItemDto> increaseQuantityListener
    ) {
        super(itemView, listener);

        tvItemName = itemView.findViewById(R.id.tvCartItemName);
        tvItemPrice = itemView.findViewById(R.id.tvCartItemPrice);
        ivMinus = itemView.findViewById(R.id.ivCartMinus);
        ivPlus = itemView.findViewById(R.id.ivCartPlus);
        tvCartItemQuantity = itemView.findViewById(R.id.tvCartQuantity);

        this.decreaseQuantityListener = decreaseQuantityListener;
        this.increaseQuantityListener = increaseQuantityListener;
    }

    @Override
    public void bindData(CartItemDto item) {
        super.bindData(item);

        tvItemName.setText(item.getName());
        tvItemPrice.setText(Utils.round(item.getPrice()) + "K");
        tvCartItemQuantity.setText(String.valueOf(item.getQuantity()));

        ivMinus.setOnClickListener(view -> decreaseQuantityListener.onClick(item, getAdapterPosition()));
        ivPlus.setOnClickListener(view -> increaseQuantityListener.onClick(item, getAdapterPosition()));
    }
}
