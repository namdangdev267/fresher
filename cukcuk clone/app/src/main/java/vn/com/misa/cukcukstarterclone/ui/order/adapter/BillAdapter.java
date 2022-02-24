package vn.com.misa.cukcukstarterclone.ui.order.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.ui.order.dto.OrderDto;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class BillAdapter extends BaseRecyclerViewAdapter<OrderDto, BillViewHolder> {
    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill, parent, false);
        return new BillViewHolder(itemView, listener);
    }
}
