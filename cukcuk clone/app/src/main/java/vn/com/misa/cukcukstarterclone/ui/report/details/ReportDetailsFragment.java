package vn.com.misa.cukcukstarterclone.ui.report.details;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;
import vn.com.misa.cukcukstarterclone.data.repository.IReportRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.ui.report.details.adapter.ReportDetailsAdapter;
import vn.com.misa.cukcukstarterclone.utils.Utils;

/**
 * - Mục đích class: Hiển thị doanh số bán hàng theo mặt hàng
 *
 * @created_by KhanhNQ on 03-Feb-21
 */
public class ReportDetailsFragment extends BaseFragment<ReportDetailsContract.View, ReportDetailsPresenter>
        implements ReportDetailsContract.View {

    public static final String TAG = "ReportDetailsFragment";

    private TextView tvDateSelection;
    private PieChart chartItemDistribution;
    private RecyclerView rcvItemReport;
    private Spinner spnDateSelection;
    private NestedScrollView nsvContent;
    private TextView tvNoData;
    private TextView tvTotalQuantity;
    private TextView tvTotalPrice;
    private TextView tvTotalDiscount;
    private TextView tvTotalRevenue;

    private ReportDetailsAdapter reportDetailsAdapter = new ReportDetailsAdapter();

    private ReportDetailsPresenter mPresenter;

    public ReportDetailsFragment() {
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_report_details;
    }

    @Override
    protected void bindViews(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });

        tvDateSelection = view.findViewById(R.id.tvDateSelection);
        chartItemDistribution = view.findViewById(R.id.chartItemDistribution);
        nsvContent = view.findViewById(R.id.nsvContent);
        tvNoData = view.findViewById(R.id.tvNoData);
        tvTotalQuantity = view.findViewById(R.id.tvTotalQuantity);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        tvTotalDiscount = view.findViewById(R.id.tvTotalDiscount);
        tvTotalRevenue = view.findViewById(R.id.tvTotalRevenue);

        spnDateSelection = view.findViewById(R.id.spnDateSelection);
        initSpinner();

        rcvItemReport = view.findViewById(R.id.rcvItemReport);
        rcvItemReport.setAdapter(reportDetailsAdapter);

        initPresenter();
    }

    private void initSpinner() {
        String[] reportSelection = getResources().getStringArray(R.array.report_by);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, reportSelection);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDateSelection.setAdapter(spinnerArrayAdapter);
        spnDateSelection.setSelection(0);
        spnDateSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                selectDateReport(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initPresenter() {
        IReportRepository reportRepository = Injector.getReportRepository(requireContext());

        mPresenter = new ReportDetailsPresenter(reportRepository);
        mPresenter.attach(this);
    }

    @Override
    protected void initData() {
        mPresenter.getCurrentDayReport();
    }

    private void selectDateReport(int pos) {
        switch (pos) {
            case 1: // Hôm qua
                mPresenter.getYesterdayReport();
                break;
            case 2: // Tuần này
                mPresenter.getThisWeekReport();
                break;
            case 3: // Tuần trước
                mPresenter.getLastWeekReport();
                break;
            case 4: // Tháng này
                mPresenter.getThisMonthReport();
                break;
            case 5: // Người dùng tự chọn khoảng thời gian
                MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
                MaterialDatePicker<Pair<Long, Long>> picker = builder.build();

                picker.show(getParentFragmentManager(), picker.toString());
                picker.addOnPositiveButtonClickListener(selection -> mPresenter.getInRange(selection.first, selection.second));
                break;
            default: // Hôm nay
                mPresenter.getCurrentDayReport();
                break;
        }
    }

    @Override
    public void updateDateTitle(String title) {
        tvDateSelection.setText(title);
    }

    @Override
    public void showEmptyReport() {
        tvNoData.setVisibility(View.VISIBLE);
        nsvContent.setVisibility(View.GONE);
    }

    @Override
    public void showPieChartData(List<PieEntry> entries) {
        tvNoData.setVisibility(View.GONE);
        nsvContent.setVisibility(View.VISIBLE);

        PieDataSet dataSet = new PieDataSet(entries, "");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#0099fc"));
        colors.add(Color.parseColor("#fc7a7a"));
        colors.add(Color.parseColor("#07d42d"));
        colors.add(Color.parseColor("#fd9369"));
        colors.add(Color.parseColor("#7a89fe"));
        colors.add(Color.parseColor("#facb00"));
        colors.add(Color.parseColor("#af7cfb"));
        colors.add(Color.parseColor("#46d7ec"));
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value > 5 ? new DecimalFormat("###,###,##0.00").format(value) + "%" : "";
            }
        });

        chartItemDistribution.getLegend().setForm(Legend.LegendForm.CIRCLE);
        chartItemDistribution.getLegend().setTextSize(14f);
        chartItemDistribution.getLegend().setFormSize(10f);
        chartItemDistribution.getLegend().setXEntrySpace(24f);
        chartItemDistribution.getLegend().setWordWrapEnabled(true);
        chartItemDistribution.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        chartItemDistribution.setUsePercentValues(true);
        chartItemDistribution.setDrawEntryLabels(false);
        chartItemDistribution.getDescription().setEnabled(false);
        chartItemDistribution.animateXY(1000, 1000);

        PieData pieData = new PieData(dataSet);
        chartItemDistribution.setData(pieData);
        chartItemDistribution.invalidate();
    }

    @Override
    public void showReportData(List<DetailsReport> data) {
        reportDetailsAdapter.loadItems(data);
    }

    @Override
    public void showReportCount(int count) {
        tvTotalQuantity.setText(String.valueOf(count));
    }

    @Override
    public void showReportTotalPrice(float total) {
        tvTotalPrice.setText(Utils.formatMoney(total));
    }

    @Override
    public void showReportTotalDiscount(float total) {
        tvTotalDiscount.setText(String.format("%s %s", getString(R.string.text_discount), Utils.formatMoney(total)));
    }

    @Override
    public void showReportTotalAmount(float total) {
        tvTotalRevenue.setText(Utils.formatMoney(total));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter.detach();
    }
}
