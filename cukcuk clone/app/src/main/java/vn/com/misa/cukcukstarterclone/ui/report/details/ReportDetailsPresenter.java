package vn.com.misa.cukcukstarterclone.ui.report.details;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;
import vn.com.misa.cukcukstarterclone.data.repository.IReportRepository;
import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.utils.Constants.DATE_DAY_FORMAT;
import static vn.com.misa.cukcukstarterclone.utils.Constants.FULL_DATE_FORMAT;
import static vn.com.misa.cukcukstarterclone.utils.Constants.SIMPLE_DATE_FORMAT;

/**
 * - Mục đích Class:
 *
 * @created_by KhanhNQ on 03-Feb-2021.
 */
public class ReportDetailsPresenter implements ReportDetailsContract.Presenter {

    private ReportDetailsContract.View view;

    private final IReportRepository reportRepository;

    public ReportDetailsPresenter(IReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void attach(ReportDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void getCurrentDayReport() {
        String fullDate = Utils.dateFormat(FULL_DATE_FORMAT, new Date());
        view.updateDateTitle(fullDate);

        String date = Utils.dateFormat(DATE_DAY_FORMAT, new Date());
        getOneDayInfo(date);
    }

    @Override
    public void getYesterdayReport() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();

        String fullDate = Utils.dateFormat(FULL_DATE_FORMAT, yesterday);
        view.updateDateTitle(fullDate);

        String date = Utils.dateFormat(DATE_DAY_FORMAT, yesterday);
        getOneDayInfo(date);
    }

    @Override
    public void getThisWeekReport() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String firstDayOfTheWeek = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String firstDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.add(Calendar.DATE, -1);
        String lastDayOfTheWeek = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String lastDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        view.updateDateTitle(firstDayOfTheWeek + " - " + lastDayOfTheWeek);
        getInfo(firstDay, lastDay);
    }

    @Override
    public void getLastWeekReport() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String firstDayOfTheWeek = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String firstDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.add(Calendar.DATE, -1);
        String lastDayOfTheWeek = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String lastDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        view.updateDateTitle(firstDayOfTheWeek + " - " + lastDayOfTheWeek);
        getInfo(firstDay, lastDay);
    }

    @Override
    public void getThisMonthReport() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfTheMonth = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String firstDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        String lastDayOfTheMonth = Utils.dateFormat(SIMPLE_DATE_FORMAT, cal.getTime());
        String lastDay = Utils.dateFormat(DATE_DAY_FORMAT, cal.getTime());

        view.updateDateTitle(firstDayOfTheMonth + " - " + lastDayOfTheMonth);
        getInfo(firstDay, lastDay);
    }

    @Override
    public void getInRange(long from, long to) {
        Date startDay = new Date(from);
        Date endDay = new Date(to);

        String firstDayInRange = Utils.dateFormat(SIMPLE_DATE_FORMAT, startDay);
        String lastDayInRange = Utils.dateFormat(SIMPLE_DATE_FORMAT, endDay);
        view.updateDateTitle(firstDayInRange + " - " + lastDayInRange);

        String firstDay = Utils.dateFormat(DATE_DAY_FORMAT, startDay);
        String lastDay = Utils.dateFormat(DATE_DAY_FORMAT, endDay);
        getInfo(firstDay, lastDay);
    }

    private void getOneDayInfo(String date) {
        reportRepository.getDetailsReport(date + "to" + date, new IOnLoadedCallback<List<DetailsReport>>() {
            @Override
            public void onSuccess(List<DetailsReport> data) {
                if (data.size() != 0) {
                    view.showReportData(data);
                    getDataSummary(data);

                    List<PieEntry> entries = new ArrayList<>();
                    for (DetailsReport report : data) {
                        entries.add(new PieEntry(report.getAmount(), report.getName()));
                    }
                    view.showPieChartData(entries);
                } else {
                    view.showEmptyReport();
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    private void getInfo(String from, String to) {
        reportRepository.getDetailsReport(from + "to" + to, new IOnLoadedCallback<List<DetailsReport>>() {
            @Override
            public void onSuccess(List<DetailsReport> data) {
                if (data.size() != 0) {
                    view.showReportData(data);
                    getDataSummary(data);

                    List<PieEntry> entries = new ArrayList<>();
                    for (DetailsReport report : data) {
                        entries.add(new PieEntry(report.getAmount(), report.getName()));
                    }
                    view.showPieChartData(entries);
                } else {
                    view.showEmptyReport();
                }
            }

            @Override
            public void onFailure(Exception e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }

    private void getDataSummary(List<DetailsReport> data) {
        int count = 0;
        float price = 0;
        float discount = 0;
        float amount = 0;
        for (DetailsReport report : data) {
            count += report.getQuantity();
            price += report.getPrice();
            discount += report.getDiscount();
            amount += report.getAmount();
        }
        view.showReportCount(count);
        view.showReportTotalPrice(price);
        view.showReportTotalDiscount(discount);
        view.showReportTotalAmount(amount);
    }
}
