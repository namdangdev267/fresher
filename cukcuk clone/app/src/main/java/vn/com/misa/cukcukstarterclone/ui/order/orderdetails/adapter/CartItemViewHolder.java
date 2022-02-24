package vn.com.misa.cukcukstarterclone.ui.order.orderdetails.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.CartItem;
import vn.com.misa.cukcukstarterclone.ui.order.dto.CartItemDto;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class CartItemViewHolder extends BaseViewHolder<CartItemDto> {

    private final TextView tvCartItemTitle;
    private final TextView tvCartItemQuantity;
    private final TextView tvCartItemTotal;

    public CartItemViewHolder(@NonNull View itemView, OnItemClickListener<CartItemDto> listener) {
        super(itemView, listener);

        tvCartItemTitle = itemView.findViewById(R.id.tvCartItemTitle);
        tvCartItemQuantity = itemView.findViewById(R.id.tvCartItemQuantity);
        tvCartItemTotal = itemView.findViewById(R.id.tvCartItemTotal);
    }

    @Override
    public void bindData(CartItemDto item) {
        super.bindData(item);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvCartItemTitle.setText(item.getName());
        tvCartItemQuantity.setText(decimalFormat.format(item.getQuantity()));
        tvCartItemTotal.setText(decimalFormat.format(item.getPrice()));
    }
}
