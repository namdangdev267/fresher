package vn.com.misa.cukcukstarterclone.ui.setup.step1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.data.model.BusinessType;

/**
 * - Mục đích Class: Adapter cho Business Recyclerview
 *
 * @created_by KhanhNQ on 12-Jan-21.
 */
public class BusinessTypeAdapter extends BaseRecyclerViewAdapter<BusinessType, BusinessViewHolder> {

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_type, parent, false);
        return new BusinessViewHolder(itemView, listener);
    }

}
