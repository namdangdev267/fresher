package vn.com.misa.cukcukstarterclone.ui.report.overall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.util.Date;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.utils.Constants.DAY_IN_WEEK_FORMAT;

/**
 * - Mục đích class: Hiển thị doanh số bán hàng tại ngày mà người dùng bấm vào trên chart
 *
 * @created_by KhanhNQ on 02-Feb-2021.
 */
@SuppressLint("ViewConstructor")
public class MarkerViewReport extends MarkerView {
    private final TextView tvDate;
    private final TextView tvAmount;

    public MarkerViewReport(Context context, int layoutResource) {
        super(context, layoutResource);

        tvDate = findViewById(R.id.tvDate);
        tvAmount = findViewById(R.id.tvAmount);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvDate.setText(Utils.dateFormat(DAY_IN_WEEK_FORMAT, new Date((long) e.getX())));
        tvAmount.setText(new DecimalFormat("###,###,###").format(e.getY()));

        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {
        if (mOffset == null) {
            mOffset = new MPPointF(-(getWidth()), -getHeight());
        }
        return mOffset;
    }
}
