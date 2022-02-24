package vn.com.misa.cukcukstarterclone.data.source.local;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.LoadingAsyncTask;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;
import vn.com.misa.cukcukstarterclone.data.model.HourReport;
import vn.com.misa.cukcukstarterclone.data.model.OverallReport;
import vn.com.misa.cukcukstarterclone.data.source.IReportDataSource;
import vn.com.misa.cukcukstarterclone.data.source.local.dao.IReportDao;

/**
 * @created_by KhanhNQ on 01-Feb-2021.
 */
public class ReportLocalDataSource implements IReportDataSource.Local {

    private final IReportDao reportDao;

    private ReportLocalDataSource(IReportDao reportDao) {
        this.reportDao = reportDao;
    }

    private static ReportLocalDataSource instance;

    public static ReportLocalDataSource getInstance(IReportDao reportDao) {
        if (instance == null) {
            instance = new ReportLocalDataSource(reportDao);
        }
        return instance;
    }

    @Override
    public void getTotalMoneyReport(String date, IOnLoadedCallback<List<OverallReport>> callback) {
        new LoadingAsyncTask<String, List<OverallReport>>(callback, param -> reportDao.getOverallReport(date)).execute(date);
    }

    @Override
    public void getCashReport(String date, IOnLoadedCallback<List<OverallReport>> callback) {
        new LoadingAsyncTask<String, List<OverallReport>>(callback, param -> reportDao.getOverallCashReport(date)).execute(date);
    }

    @Override
    public void getReportByHours(String date, IOnLoadedCallback<List<HourReport>> callback) {
        new LoadingAsyncTask<String, List<HourReport>>(callback, param -> reportDao.getOverallReportByHours(date)).execute(date);
    }

    @Override
    public void getDetailsReport(String date, IOnLoadedCallback<List<DetailsReport>> callback) {
        new LoadingAsyncTask<String, List<DetailsReport>>(callback, param -> reportDao.getDetailsReport(date)).execute(date);
    }
}
