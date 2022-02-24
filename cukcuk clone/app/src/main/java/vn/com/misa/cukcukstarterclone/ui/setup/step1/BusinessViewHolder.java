package vn.com.misa.cukcukstarterclone.ui.setup.step1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.BusinessType;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * - Mục đích Class: ViewHolder cho item BusinessType
 * - Sử dụng khi: được gọi từ {@link BusinessTypeAdapter}
 *
 * @created_by KhanhNQ on 12-Jan-21.
 */
public class BusinessViewHolder extends BaseViewHolder<BusinessType> {

    private final View viewIsNotAvailable;
    private final ImageView ivBusinessType;
    private final TextView tvBusinessTitle;

    /**
     * - Mục đích method: ánh xạ view
     *
     * @created_by KhanhNQ on 12-Jan-21
     */
    public BusinessViewHolder(@NonNull View itemView, OnItemClickListener<BusinessType> listener) {
        super(itemView, listener);

        viewIsNotAvailable = itemView.findViewById(R.id.viewIsNotAvailable);
        ivBusinessType = itemView.findViewById(R.id.ivBusinessType);
        tvBusinessTitle = itemView.findViewById(R.id.tvBusinessTitle);
    }

    /**
     * - Mục đích method: hiển thị dữ liệu của item lên view
     *
     * @param item thông tin về Loại nhà hàng
     * @created_by KhanhNQ on 12-Jan-21
     */
    @Override
    public void bindData(BusinessType item) {
        try {
            super.bindData(item);

            if (!item.isAvailable()) viewIsNotAvailable.setVisibility(View.VISIBLE);
            tvBusinessTitle.setText(item.getTitle());
            ivBusinessType.setImageResource(item.getImageIcon());
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }
}
