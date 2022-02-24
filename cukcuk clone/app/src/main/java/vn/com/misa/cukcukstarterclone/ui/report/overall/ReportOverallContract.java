package vn.com.misa.cukcukstarterclone.ui.report.overall;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.OverallReport;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class ReportOverallContract {
    interface View extends BaseContract.View {
        void updateDateTitle(String title);

        void showTotalMoney(String value);

        void showCashMoney(String value);

        void showOtherMoney(String value);

        void showEmptyReport();

        void hideReportByDay();

        void showReportByDay(List<Entry> reportEntries);

        void showReportByHour(List<BarEntry> reportEntries);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getCurrentDayReport();

        void getYesterdayReport();

        void getThisWeekReport();

        void getLastWeekReport();

        void getThisMonthReport();

        void getInRange(long from, long to);
    }
}
