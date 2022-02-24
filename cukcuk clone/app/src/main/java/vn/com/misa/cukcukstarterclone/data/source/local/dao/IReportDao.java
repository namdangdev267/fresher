package vn.com.misa.cukcukstarterclone.data.source.local.dao;

import java.util.List;

import vn.com.misa.cukcukstarterclone.data.model.DetailsReport;
import vn.com.misa.cukcukstarterclone.data.model.HourReport;
import vn.com.misa.cukcukstarterclone.data.model.OverallReport;

/**
 * @created_by KhanhNQ on 01-Feb-2021.
 */
public interface IReportDao {
    List<OverallReport> getOverallReport(String date);

    List<OverallReport> getOverallCashReport(String date);

    List<HourReport> getOverallReportByHours(String date);

    List<DetailsReport> getDetailsReport(String date);
}
