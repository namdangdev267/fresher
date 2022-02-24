package vn.com.misa.cukcukstarterclone.ui.main.addorder.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.ui.main.addorder.dto.MenuGroupDto;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 26-Jan-21.
 */
public class MenuGroupViewHolder extends BaseViewHolder<MenuGroupDto> {

    private final TextView tvMenuGroupName;
    private final TextView tvMenuGroupIndicator;

    public MenuGroupViewHolder(@NonNull View itemView, OnItemClickListener<MenuGroupDto> listener) {
        super(itemView, listener);

        tvMenuGroupName = itemView.findViewById(R.id.tvMenuGroupName);
        tvMenuGroupIndicator = itemView.findViewById(R.id.tvMenuGroupIndicator);
    }

    @Override
    public void bindData(MenuGroupDto item) {
        try {
            super.bindData(item);

            tvMenuGroupName.setText(item.getTitle());

            String prefix = itemView.getResources().getString(R.string.prefix_drawable_name);
            if (item.isSelected()) {
                tvMenuGroupName.setTextColor(itemView.getResources().getColor(R.color.blue));
                String postfix = itemView.getResources().getString(R.string.postfix_drawable_name);
                tvMenuGroupName.setCompoundDrawablesWithIntrinsicBounds(0, getDrawable(prefix + item.getIconUrl() + postfix), 0, 0);
            } else {
                tvMenuGroupName.setTextColor(itemView.getResources().getColor(R.color.dark_grey));
                tvMenuGroupName.setCompoundDrawablesWithIntrinsicBounds(0, getDrawable(prefix + item.getIconUrl()), 0, 0);
            }

            int itemCount = item.getItemCount();
            if (itemCount > 0) {
                tvMenuGroupIndicator.setVisibility(View.VISIBLE);
                tvMenuGroupIndicator.setText(String.valueOf(item.getItemCount()));
            } else {
                tvMenuGroupIndicator.setVisibility(View.GONE);
            }
        } catch (
                Exception e) {
            Utils.handleException(e);
        }

    }

    /**
     * - Mục đích method: Lấy drawable theo tên
     *
     * @param name tên của drawable
     * @return id của drawable
     * @created_by KhanhNQ on 26-Jan-21
     */
    private int getDrawable(String name) {
        Context context = itemView.getContext();
        Resources resources = context.getResources();
        return resources.getIdentifier(name, "drawable", context.getPackageName());
    }
}
