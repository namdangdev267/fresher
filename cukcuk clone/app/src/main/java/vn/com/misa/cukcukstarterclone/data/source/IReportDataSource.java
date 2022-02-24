package vn.com.misa.cukcukstarterclone.data.source;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;
import vn.com.misa.cukcukstarterclone.data.model.HourReport;
import vn.com.misa.cukcukstarterclone.data.model.OverallReport;

/**
 * - Mục đích Class:
 *
 * @created_by KhanhNQ on 01-Feb-2021.
 */
public interface IReportDataSource {
    interface Local {
        void getTotalMoneyReport(String date, IOnLoadedCallback<List<OverallReport>> callback);

        void getCashReport(String date, IOnLoadedCallback<List<OverallReport>> callback);

        void getReportByHours(String date, IOnLoadedCallback<List<HourReport>> callback);

        void getDetailsReport(String date, IOnLoadedCallback<List<DetailsReport>> callback);
    }

    interface Remote {
    }
}
