package vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.adapter;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.ui.menu.groupdetails.dto.GroupIcon;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class GroupIconViewHolder extends BaseViewHolder<GroupIcon> {

    private final ImageView ivSelectedGroup;
    private final ImageView ivUnselectedGroup;
    private final ImageView ivGroupIcon;

    public GroupIconViewHolder(@NonNull View itemView, OnItemClickListener<GroupIcon> listener) {
        super(itemView, listener);

        ivSelectedGroup = itemView.findViewById(R.id.ivSelectedGroup);
        ivUnselectedGroup = itemView.findViewById(R.id.ivUnselectedGroup);
        ivGroupIcon = itemView.findViewById(R.id.ivGroupIcon);
    }

    @Override
    public void bindData(GroupIcon item) {
        super.bindData(item);

        ivGroupIcon.setImageDrawable(item.getDrawable(itemView.getContext()));

        int color;
        if (item.isSelected()) {
            ivSelectedGroup.setVisibility(View.VISIBLE);
            ivUnselectedGroup.setVisibility(View.INVISIBLE);
            color = ContextCompat.getColor(itemView.getContext(), R.color.white);
        } else {
            ivSelectedGroup.setVisibility(View.INVISIBLE);
            ivUnselectedGroup.setVisibility(View.VISIBLE);
            color = ContextCompat.getColor(itemView.getContext(), R.color.blue);
        }
        ivGroupIcon.setImageTintList(ColorStateList.valueOf(color));
    }
}
