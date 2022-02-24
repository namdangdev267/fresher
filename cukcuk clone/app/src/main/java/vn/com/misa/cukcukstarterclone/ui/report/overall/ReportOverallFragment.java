package vn.com.misa.cukcukstarterclone.ui.report.overall;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import vn.com.misa.cukcukstarterclone.R;
import vn.com.misa.cukcukstarterclone.base.BaseFragment;
import vn.com.misa.cukcukstarterclone.data.repository.ReportRepository;
import vn.com.misa.cukcukstarterclone.di.Injector;
import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.utils.Constants.DAY_IN_WEEK_FORMAT;

/**
 * @created_by KhanhNQ on 31-Jan-21
 */
public class ReportOverallFragment extends BaseFragment<ReportOverallContract.View, ReportOverallPresenter>
        implements ReportOverallContract.View {

    public static String TAG = "ReportOverallFragment";

    private TextView tvNoData;
    private TextView tvDateSelection;
    private TextView tvReportTotal;
    private TextView tvReportCash;
    private TextView tvReportOther;
    private ConstraintLayout clContents;
    private LinearLayout llIncomeByDay;
    private LineChart chartIncomeByDay;
    private LinearLayout llIncomeByHour;
    private BarChart chartIncomeByHour;

    private ReportOverallPresenter mPresenter;

    public ReportOverallFragment() {
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_report_overall;
    }

    /**
     * - Mục đích method: Ánh xạ, khởi tạo Views
     *
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Override
    protected void bindViews(View view) {
        try {
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(v -> {
                requireActivity().getSupportFragmentManager().popBackStack(TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            });

            tvNoData = view.findViewById(R.id.tvNoData);
            tvDateSelection = view.findViewById(R.id.tvDateSelection);
            Spinner spnDateSelection = view.findViewById(R.id.spnDateSelection);
            tvReportTotal = view.findViewById(R.id.tvReportTotal);
            tvReportCash = view.findViewById(R.id.tvReportCash);
            tvReportOther = view.findViewById(R.id.tvReportOther);
            clContents = view.findViewById(R.id.clContents);
            llIncomeByDay = view.findViewById(R.id.llIncomeByDay);
            chartIncomeByDay = view.findViewById(R.id.chartIncomeByDay);
            llIncomeByHour = view.findViewById(R.id.llIncomeByHour);
            chartIncomeByHour = view.findViewById(R.id.chartIncomeByHour);

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
        } catch (Exception e) {
            Utils.handleException(e);
        }

        initPresenter();
    }

    /**
     * - Mục đích method: khởi tạo dữ liệu mặc định, lấy dữ liệu ngày hiện tại
     *
     * @created_by KhanhNQ on 31-Jan-21
     */
    private void initPresenter() {
        try {
            ReportRepository reportRepository = Injector.getReportRepository(requireContext());

            mPresenter = new ReportOverallPresenter(reportRepository);
            mPresenter.attach(this);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    /**
     * - Mục đích method: Cập nhật lại số liệu trên theo lựa chọn người dùng
     *
     * @param index thứ tự của lựa chọn trong danh sách
     * @created_by KhanhNQ on 31-Jan-21
     */
    private void selectDateReport(int index) {
        switch (index) {
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
    protected void initData() {
        mPresenter.getCurrentDayReport();
    }

    @Override
    public void updateDateTitle(String title) {
        tvDateSelection.setText(title);
    }

    /**
     * - Mục đích method: Hiển thị tổng số doanh thu
     *
     * @param value Số tiền
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Override
    public void showTotalMoney(String value) {
        tvNoData.setVisibility(View.GONE);
        clContents.setVisibility(View.VISIBLE);

        tvReportTotal.setText(value);
    }

    /**
     * - Mục đích method: Hiển thị doanh thu tiền mặt
     *
     * @param value Số tiền
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Override
    public void showCashMoney(String value) {
        tvReportCash.setText(value);
    }

    /**
     * - Mục đích method: Hiển thị doanh thu từ khoản khác
     *
     * @param value Số tiền
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Override
    public void showOtherMoney(String value) {
        tvReportOther.setText(value);
    }

    /**
     * - Mục đích method: Hiển thị không có dữ liệu
     *
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Override
    public void showEmptyReport() {
        tvNoData.setVisibility(View.VISIBLE);
        clContents.setVisibility(View.GONE);
    }

    /**
     * - Mục đích method: Ẩn bảng dữ liệu theo ngày
     * - Sử dụng khi: Người dùng chọn xem báo cáo theo Hôm qua hoặc Hôm nay
     *
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Override
    public void hideReportByDay() {
        llIncomeByDay.setVisibility(View.GONE);
    }

    /**
     * - Mục đích method: Hiển thị bảng dữ liệu theo ngày
     *
     * @created_by KhanhNQ on 31-Jan-21
     */
    @Override
    public void showReportByDay(List<Entry> reportEntries) {
        llIncomeByDay.setVisibility(View.VISIBLE);
        LineDataSet dataSet = new LineDataSet(reportEntries, "");
        dataSet.setDrawCircles(false);
        dataSet.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        dataSet.setFillColor(ContextCompat.getColor(getContext(), R.color.blue));
        dataSet.setLineWidth(2f);
        dataSet.setValueTextSize(10f);
        dataSet.setDrawFilled(true);
        dataSet.setFormLineWidth(5f);
        dataSet.setFormSize(5.f);
        dataSet.setDrawValues(false);

        // Hide Right Axis
        chartIncomeByDay.getAxisRight().setEnabled(false);
        chartIncomeByDay.getDescription().setEnabled(false);
        chartIncomeByDay.getLegend().setEnabled(false);
        chartIncomeByDay.setTouchEnabled(true);
        chartIncomeByDay.animateY(1000);

        IMarker marker = new MarkerViewReport(getContext(), R.layout.marker_view_report);
        chartIncomeByDay.setMarker(marker);

        XAxis xAxis = chartIncomeByDay.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.enableGridDashedLine(5f, 6f, 0f);
        xAxis.setLabelCount(reportEntries.size(), true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                try {
                    return Utils.dateFormat(DAY_IN_WEEK_FORMAT, new Date((long) value));
                } catch (Exception e) {
                    Utils.handleException(e);
                }
                return "";
            }
        });

        YAxis yAxis = chartIncomeByDay.getAxisLeft();
        yAxis.removeAllLimitLines();
        yAxis.setAxisMinimum(0f);
        yAxis.enableGridDashedLine(5f, 5f, 0f);
        yAxis.setDrawZeroLine(false);
        yAxis.setDrawLimitLinesBehindData(false);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                try {
                    return new DecimalFormat("###,###,###").format(value / 1000);
                } catch (Exception e) {
                    Utils.handleException(e);
                }
                return "";
            }
        });

        LineData lineData = new LineData(dataSet);
        chartIncomeByDay.setData(lineData);
        chartIncomeByDay.invalidate();
    }

    /**
     * - Mục đích method: Hiển thị thông tin thống kê theo giờ
     *
     * @param reportEntries danh sách thống kê
     * @created_by KhanhNQ on 04-Feb-21
     */
    @Override
    public void showReportByHour(List<BarEntry> reportEntries) {
        llIncomeByHour.setVisibility(View.VISIBLE);
        BarDataSet dataSet = new BarDataSet(reportEntries, "");
        dataSet.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        dataSet.setHighLightColor(ContextCompat.getColor(getContext(), R.color.darker_blue));
        dataSet.setValueTextSize(10f);
        dataSet.setFormLineWidth(5f);
        dataSet.setFormSize(5.f);
        dataSet.setDrawValues(false);

        // Hide Right Axis
        chartIncomeByHour.getAxisRight().setEnabled(false);
        chartIncomeByHour.getDescription().setEnabled(false);
        chartIncomeByHour.getLegend().setEnabled(false);
        chartIncomeByHour.setTouchEnabled(true);
        chartIncomeByHour.animateY(1000);

        IMarker marker = new MarkerHourReport(getContext(), R.layout.marker_view_report);
        chartIncomeByHour.setMarker(marker);

        XAxis xAxis = chartIncomeByHour.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.enableGridDashedLine(5f, 6f, 0f);
        xAxis.setLabelCount(reportEntries.size());
        xAxis.setCenterAxisLabels(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                try {
                    return (int) value + ":00";
                } catch (Exception e) {
                    Utils.handleException(e);
                }
                return "";
            }
        });

        YAxis yAxis = chartIncomeByHour.getAxisLeft();
        yAxis.removeAllLimitLines();
        yAxis.setAxisMinimum(0f);
        yAxis.enableGridDashedLine(5f, 5f, 0f);
        yAxis.setDrawZeroLine(false);
        yAxis.setDrawLimitLinesBehindData(false);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                try {
                    return new DecimalFormat("###,###,###").format(value / 1000);
                } catch (Exception e) {
                    Utils.handleException(e);
                }
                return "";
            }
        });

        BarData barData = new BarData(dataSet);
        chartIncomeByHour.setData(barData);
        chartIncomeByHour.invalidate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter.detach();
    }
}
