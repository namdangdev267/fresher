package vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuItemDto;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 26-Jan-21.
 */
public class MenuItemViewHolder extends BaseViewHolder<MenuItemDto> {

    private final ImageView ivMenuItemImg;
    private final TextView tvMenuItemName;
    private final TextView tvMenuItemPrice;
    private final TextView tvItemCount;

    /**
     * - Mục đích method: Ánh xạ view
     *
     * @created_by KhanhNQ on 26-Jan-21
     */
    public MenuItemViewHolder(@NonNull View itemView,
                              OnItemClickListener<MenuItemDto> listener) {
        super(itemView, listener);

        ivMenuItemImg = itemView.findViewById(R.id.ivMenuItemImg);
        tvMenuItemName = itemView.findViewById(R.id.tvMenuItemName);
        tvMenuItemPrice = itemView.findViewById(R.id.tvMenuItemPrice);
        tvItemCount = itemView.findViewById(R.id.tvItemCount);
    }

    /**
     * - Mục đích method: Hiển thị dữ liệu của item lên view
     *
     * @param item thông tin về Item hiển thị lên view
     * @created_by KhanhNQ on 26-Jan-21
     */
    @Override
    public void bindData(MenuItemDto item) {
        try {
            super.bindData(item);

            tvMenuItemName.setText(item.getName());
            String priceString = Utils.round(item.getPrice()) + "K";
            tvMenuItemPrice.setText(priceString);

            if (item.getImageUrl().startsWith("file://")) {
                Glide.with(itemView.getContext())
                        .load(item.getImageUrl())
                        .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(8)))
                        .placeholder(R.drawable.placeholder)
                        .into(ivMenuItemImg);
            } else {
                Uri imageUri = Uri.fromFile(new File(item.getImageUrl()));
                Glide.with(itemView.getContext())
                        .load(imageUri)
                        .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(8)))
                        .placeholder(R.drawable.placeholder)
                        .into(ivMenuItemImg);
            }

            int quantity = item.getQuantity();
            if (quantity > 0) {
                tvItemCount.setVisibility(View.VISIBLE);
                tvItemCount.setText(String.valueOf(quantity));
            } else {
                tvItemCount.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }
}
