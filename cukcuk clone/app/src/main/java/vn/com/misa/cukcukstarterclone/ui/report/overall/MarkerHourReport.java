package vn.com.misa.cukcukstarterclone.ui.report.overall;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

import vn.com.misa.cukcukstarterclone.R;

/**
 * @created_by KhanhNQ on 04-Feb-2021.
 */
public class MarkerHourReport extends MarkerView {

    private final TextView tvDate;
    private final TextView tvAmount;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MarkerHourReport(Context context, int layoutResource) {
        super(context, layoutResource);

        tvDate = findViewById(R.id.tvDate);
        tvAmount = findViewById(R.id.tvAmount);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvDate.setText((int) e.getX() + ":00");
        tvAmount.setText(new DecimalFormat("###,###,###").format(e.getY()));

        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {
        if (mOffset == null) {
            mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
        }
        return mOffset;
    }
}
