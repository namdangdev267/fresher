package vn.com.misa.cukcukstarterclone.ui.menu.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.MenuGroup;

/**
 * @created_by KhanhNQ on 30-Jan-2021.
 */
public class MenuGroupViewHolder extends BaseViewHolder<MenuGroup> {

    private final ImageView ivGroupIcon;
    private final TextView tvGroupTitle;
    private final ImageView ivDeleteGroup;
    private final ImageView ivEditGroup;

    private final OnItemClickListener<MenuGroup> editListener;
    private final OnItemClickListener<MenuGroup> deleteListener;

    public MenuGroupViewHolder(@NonNull View itemView,
                               OnItemClickListener<MenuGroup> listener,
                               OnItemClickListener<MenuGroup> editListener,
                               OnItemClickListener<MenuGroup> deleteListener) {
        super(itemView, listener);

        this.editListener = editListener;
        this.deleteListener = deleteListener;

        ivGroupIcon = itemView.findViewById(R.id.ivGroupIcon);
        tvGroupTitle = itemView.findViewById(R.id.tvGroupTitle);
        ivDeleteGroup = itemView.findViewById(R.id.ivDeleteGroup);
        ivEditGroup = itemView.findViewById(R.id.ivEditGroup);
    }

    @Override
    public void bindData(MenuGroup item) {
        super.bindData(item);

        ivGroupIcon.setBackground(item.getDrawable(itemView.getContext()));
        tvGroupTitle.setText(item.getTitle());

        ivEditGroup.setOnClickListener(v -> editListener.onClick(item, getAdapterPosition()));
        ivDeleteGroup.setOnClickListener(v -> deleteListener.onClick(item, getAdapterPosition()));
    }
}
