package vn.com.misa.cukcukstarterclone.ui.report.overall;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.OverallReport;
import vn.com.misa.cukcukstarterclone.data.repository.ReportRepository;
import vn.com.misa.cukcukstarterclone.utils.Utils;

import static vn.com.misa.cukcukstarterclone.utils.Constants.DATE_DAY_FORMAT;
import static vn.com.misa.cukcukstarterclone.utils.Constants.FULL_DATE_FORMAT;
import static vn.com.misa.cukcukstarterclone.utils.Constants.SIMPLE_DATE_FORMAT;

/**
 * @created_by KhanhNQ on 31-Jan-2021.
 */
public class ReportPresenter implements ReportContract.Presenter {

    private ReportContract.View view;

    private final ReportRepository reportRepository;

    public ReportPresenter(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void attach(ReportContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    /**
     * - Mục đích method: Lấy báo cáo doanh thu ngày hiện tại
     *
     * @created_by KhanhNQ on 31-Jan-2021.
     */
    @Override
    public void getCurrentDayReport() {
        String fullDate = Utils.dateFormat(FULL_DATE_FORMAT, new Date());
        view.updateDateTitle(fullDate);

        String date = Utils.dateFormat(DATE_DAY_FORMAT, new Date());

        getOneDayInfo(date);
    }

    /**
     * - Mục đích method: Lấy báo cáo doanh thu ngày hôm qua
     *
     * @created_by KhanhNQ on 31-Jan-2021.
     */
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

    /**
     * - Mục đích method: Lấy báo cáo doanh thu tuần này
     *
     * @created_by KhanhNQ on 31-Jan-2021.
     */
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

    /**
     * - Mục đích method: Lấy báo cáo doanh thu tuần trước
     *
     * @created_by KhanhNQ on 31-Jan-2021.
     */
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

    /**
     * - Mục đích method: Lấy báo cáo doanh thu tháng này
     *
     * @created_by KhanhNQ on 31-Jan-2021.
     */
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

    /**
     * - Mục đích method: Lấy báo cáo doanh thu trong khoảng
     *
     * @param from ngày bắt đầu
     * @param to   ngày kết thúc
     * @created_by KhanhNQ on 31-Jan-2021.
     */
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

    /**
     * - Mục đích method: Lấy báo cáo doanh thu một ngày bất kì
     *
     * @param date ngày cần lấy thông tin
     * @created_by KhanhNQ on 31-Jan-2021.
     */
    private void getOneDayInfo(String date) {
        view.hideReportByDay();

        reportRepository.getTotalMoneyReport(date + "to" + date, new IOnLoadedCallback<List<OverallReport>>() {
            @Override
            public void onSuccess(List<OverallReport> data) {
                if (data.size() != 0) {
                    float total = data.get(0).getAmount();
                    view.showTotalMoney(Utils.formatMoney(total));

                    reportRepository.getCashReport(date + "to" + date, new IOnLoadedCallback<List<OverallReport>>() {
                        @Override
                        public void onSuccess(List<OverallReport> data) {
                            if (data.size() != 0) {
                                float cash = data.get(0).getAmount();
                                view.showCashMoney(Utils.formatMoney(cash));
                                view.showOtherMoney(Utils.formatMoney(total - cash));
                            } else {
                                view.showCashMoney("0");
                                view.showOtherMoney(Utils.formatMoney(total));
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            view.showErrorMessage(e.getMessage());
                        }
                    });
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

    /**
     * - Mục đích method: Lấy báo cáo doanh thu trong khoảng
     *
     * @param from ngày bắt đầu
     * @param end  ngày kết thúc
     * @created_by KhanhNQ on 31-Jan-2021.
     */
    private void getInfo(String from, String end) {
        reportRepository.getTotalMoneyReport(from + "to" + end, new IOnLoadedCallback<List<OverallReport>>() {
            @Override
            public void onSuccess(List<OverallReport> data) {
                if (data.size() != 0) {
                    List<Entry> entries = new ArrayList<>();
                    for (OverallReport report : data) {
                        entries.add(new Entry(report.getDay().getTime(), report.getAmount()));
                    }
                    view.showReportByDay(entries);

                    float total = 0;
                    for (OverallReport report : data) {
                        total += report.getAmount();
                    }
                    view.showTotalMoney(Utils.formatMoney(total));

                    float finalTotal = total;
                    reportRepository.getCashReport(from + "to" + end, new IOnLoadedCallback<List<OverallReport>>() {
                        @Override
                        public void onSuccess(List<OverallReport> data) {
                            if (data.size() != 0) {
                                float cash = 0;
                                for (OverallReport report : data) {
                                    cash += report.getAmount();
                                }
                                view.showCashMoney(Utils.formatMoney(cash));
                                view.showOtherMoney(Utils.formatMoney(finalTotal - cash));
                            } else {
                                view.showCashMoney("0");
                                view.showOtherMoney(Utils.formatMoney(finalTotal));
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            view.showErrorMessage(e.getMessage());
                        }
                    });
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
}
