package vn.com.misa.cukcukstarterclone.ui.report.details.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseRecyclerViewAdapter;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;

/**
 * @created_by KhanhNQ on 03-Feb-2021.
 */
public class ReportDetailsAdapter extends BaseRecyclerViewAdapter<DetailsReport, ReportDetailsViewHolder> {
    @NonNull
    @Override
    public ReportDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup root;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_report_details, parent, false);
        return new ReportDetailsViewHolder(view, listener);
    }
}
