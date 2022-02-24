package vn.com.misa.cukcukstarterclone.ui.report.details.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseViewHolder;
import vn.com.misa.cukcukstarterclone.base.OnItemClickListener;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * @created_by KhanhNQ on 03-Feb-2021.
 */
public class ReportDetailsViewHolder extends BaseViewHolder<DetailsReport> {

    private final TextView tvItemReportNumber;
    private final TextView tvItemReportName;
    private final TextView tvItemReportQuantity;
    private final TextView tvItemReportPrice;
    private final TextView tvItemReportDiscount;
    private final TextView tvItemReportAmount;

    public ReportDetailsViewHolder(@NonNull View itemView, OnItemClickListener<DetailsReport> listener) {
        super(itemView, listener);

        tvItemReportNumber = itemView.findViewById(R.id.tvItemReportNumber);
        tvItemReportName = itemView.findViewById(R.id.tvItemReportName);
        tvItemReportQuantity = itemView.findViewById(R.id.tvItemReportQuantity);
        tvItemReportPrice = itemView.findViewById(R.id.tvItemReportPrice);
        tvItemReportDiscount = itemView.findViewById(R.id.tvItemReportDiscount);
        tvItemReportAmount = itemView.findViewById(R.id.tvItemReportAmount);
    }

    @Override
    public void bindData(DetailsReport item) {
        super.bindData(item);

        switch (getAdapterPosition() + 1) {
            case 1:
                tvItemReportNumber.setBackgroundResource(R.drawable.text_view_report_number_blue);
                tvItemReportNumber.setTextColor(itemView.getResources().getColor(R.color.blue));
                break;
            case 2:
                tvItemReportNumber.setBackgroundResource(R.drawable.text_view_report_number_pink);
                tvItemReportNumber.setTextColor(itemView.getResources().getColor(R.color.light_pink));
                break;
            case 3:
                tvItemReportNumber.setBackgroundResource(R.drawable.text_view_report_number_green);
                tvItemReportNumber.setTextColor(itemView.getResources().getColor(R.color.green));
                break;
            default:
                tvItemReportNumber.setBackgroundResource(R.drawable.text_view_report_number);
                break;
        }
        tvItemReportNumber.setText(String.valueOf(getAdapterPosition() + 1));
        tvItemReportName.setText(item.getName());
        tvItemReportQuantity.setText(String.valueOf(item.getQuantity()));
        tvItemReportPrice.setText(Utils.formatMoney(item.getPrice()));
        tvItemReportDiscount.setText(Utils.formatMoney(item.getDiscount()));
        tvItemReportAmount.setText(Utils.formatMoney(item.getAmount()));
    }
}
