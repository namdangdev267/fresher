package vn.com.misa.cukcukstarterclone.ui.report.details;

import com.github.mikephil.charting.data.PieEntry;

import java.util.List;

import vn.com.misa.cukcukstarterclone.base.BaseContract;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;

/**
 * - Mục đích Class:
 *
 * @created_by KhanhNQ on 03-Feb-2021.
 */
public class ReportDetailsContract {
    interface View extends BaseContract.View {
        void updateDateTitle(String title);

        void showEmptyReport();

        void showPieChartData(List<PieEntry> entries);

        void showReportData(List<DetailsReport> data);

        void showReportCount(int count);

        void showReportTotalPrice(float total);

        void showReportTotalDiscount(float total);

        void showReportTotalAmount(float total);
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
