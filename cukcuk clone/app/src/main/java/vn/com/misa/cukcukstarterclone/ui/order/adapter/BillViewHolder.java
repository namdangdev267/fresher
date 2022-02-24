package vn.com.misa.cukcukstarterclone.ui.order.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.ui.order.dto.OrderDto;

/**
 * @created_by KhanhNQ on 29-Jan-2021.
 */
public class BillViewHolder extends BaseViewHolder<OrderDto> {

    private final TextView tvBillId;
    private final TextView tvBillTotal;

    public BillViewHolder(@NonNull View itemView, OnItemClickListener<OrderDto> listener) {
        super(itemView, listener);

        tvBillId = itemView.findViewById(R.id.tvBillId);
        tvBillTotal = itemView.findViewById(R.id.tvBillTotal);
    }

    @Override
    public void bindData(OrderDto item) {
        super.bindData(item);

        tvBillId.setText(item.getOrderId().substring(0, 8) + "...");
        tvBillTotal.setText(new DecimalFormat("###,###,###").format(item.getTotalAmount()));
    }
}
