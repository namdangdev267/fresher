package vn.com.misa.cukcukstarterclone.ui.main.listorders.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.Cart;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 25-Jan-21.
 */
public class CartViewHolder extends BaseViewHolder<Cart> {

    private final ImageView ivCartItem;
    private final TextView tvTableNumber;
    private final TextView tvCartTitle;
    private final TextView tvCartAmount;
    private final TextView tvCancel;
    private final TextView tvCheckout;

    private final OnItemClickListener<Cart> cancelCartListener;
    private final OnItemClickListener<Cart> checkoutCartListener;

    public CartViewHolder(@NonNull View itemView,
                          OnItemClickListener<Cart> listener,
                          OnItemClickListener<Cart> cancelCartListener,
                          OnItemClickListener<Cart> checkoutCartListener) {
        super(itemView, listener);

        ivCartItem = itemView.findViewById(R.id.ivCartItem);
        tvTableNumber = itemView.findViewById(R.id.tvCartTableNumber);
        tvCartTitle = itemView.findViewById(R.id.tvCartTitle);
        tvCartAmount = itemView.findViewById(R.id.tvCartAmount);
        tvCancel = itemView.findViewById(R.id.tvCancel);
        tvCheckout = itemView.findViewById(R.id.tvCheckout);

        this.cancelCartListener = cancelCartListener;
        this.checkoutCartListener = checkoutCartListener;
    }

    @Override
    public void bindData(Cart item) {
        try {
            super.bindData(item);

            if (item.getTableNumber() != 0) {
                tvTableNumber.setBackgroundResource(R.drawable.text_view_light_blue_circle_background);
                tvTableNumber.setText(String.valueOf(item.getTableNumber()));
                ivCartItem.setVisibility(View.INVISIBLE);
            } else {
                tvTableNumber.setText("");
                ivCartItem.setVisibility(View.VISIBLE);
            }
            if (item.getType() == Cart.CartType.ORDER) {
                tvTableNumber.setBackgroundResource(R.drawable.text_view_light_blue_circle_background);
                ivCartItem.setImageResource(R.drawable.ic_order_table);
            } else {
                tvTableNumber.setBackgroundResource(R.drawable.text_view_yellow_circle_background);
                ivCartItem.setImageResource(R.drawable.ic_order_take_away);
            }

            tvCartTitle.setText(item.getTitle());
            tvCartAmount.setText(new DecimalFormat("###,###,###").format(item.getTotalAmount()));

            tvCancel.setOnClickListener(v -> cancelCartListener.onClick(item, getAdapterPosition()));
            tvCheckout.setOnClickListener(v -> checkoutCartListener.onClick(item, getAdapterPosition()));

        } catch (Exception e) {
            Utils.handleException(e);
        }
    }
}
